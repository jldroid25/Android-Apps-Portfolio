package com.jldroid25.android.boutique.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.jldroid25.android.boutique.data.BoutiqueContract.InventoryEntry;

import android.util.Log;

public class BoutiqueProvider extends ContentProvider {

    //Database Helper to help us accessing the database
    private BoutiqueDbHelper dbHelper;

    //For Log message(s)
    public static final String LOG_TAG = BoutiqueProvider.class.getSimpleName();

    // Initialize  the provider and  database helper object.
    @Override
    public boolean onCreate() {
        // To access our database, we instantiate an object dBHelper
        // and pass the getContext() param to retrieve the current activity.
        dbHelper = new BoutiqueDbHelper(getContext());
        return true;
    } //end onCreate

    //URI matcher code for the content URI for all products in the boutique table
    private static final int PRODUCTS = 100;
    //URI matcher code for the content URI for a single product in the boutique table
    private static final int PRODUCT_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    //Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.
        // Let's add 2 content URIS to URI matcher.

        //The content URI of the form "content://com.example.android.boutique/boutique" will map to the
        // integer code PRODUCTS = 100. This URI is used to provide access to MULTIPLE rows
        // of the product table.
        sUriMatcher.addURI(BoutiqueContract.CONTENT_AUTHORITY, BoutiqueContract.PATH_PRODUCTS, PRODUCTS);

        //Same situation as Above but using PRODUCT_ID for a single row only.
        //Also we are using the "#" wildcard is used where "#" can be substituted for an integer.
        sUriMatcher.addURI(BoutiqueContract.CONTENT_AUTHORITY,
                BoutiqueContract.PATH_PRODUCTS + "/#", PRODUCT_ID);
    } //end static


    // Perform the query for the given URI.
    //Use the given projection, selection, selection arguments, and sort order.
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        //Get readable database
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        //This cursor will hold result of the query
        Cursor cursor;

        //Figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);

        switch (match) {
            case PRODUCTS:

                //For the PRODUCTS code, query the boutique table directly with the given
                //projection, selection, selection arguments, and sort order. The cursor
                //could contain multiple rows of the boutique table.
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;

            //For every "?" in the selection, we need to have an element in the selection
            //arguments that will fill in the "?". Since we have 1 question mark in the
            //selection, we have 1 String in the selection arguments' String array.
            case PRODUCT_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};

                // This will perform a query on the boutique table where the _id equals
                // an id number to return a Cursor containing that row of the table.
                cursor = database.query(InventoryEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot Query Unknown URI " + uri);
        }
        //Set Notification URI on the Cursor.
        // so we know what content URI the cursor was created for.
        // If the data at this URI changes, then we know we need to update the Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    //Insert new data into the provider with given ContentValues.
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return addProduct(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    //Define our addProduct()
    private Uri addProduct(Uri uri, ContentValues values) {

        //Data sanitation Checking that Product name is not null
        String name = values.getAsString(InventoryEntry.COLUMN_PRODUCT_NAME);
        if (name == null) {
            throw new IllegalArgumentException("Product Name is required");
        }
        //Data sanitation Checking that Product Price is not null
        Float price = values.getAsFloat(InventoryEntry.COLUMN_PRICE);
        if (price != null && price < 0) {
            throw new IllegalArgumentException("Product Price is required");
        }
        //Data sanitation Checking that Product Quantity is not null & not zero
        Integer quantity = values.getAsInteger(InventoryEntry.COLUMN_QUANTITY);
        if (quantity != null && quantity < 0) {
            throw new IllegalArgumentException("Product requires a valid quantity");
        }
        String supplierName = values.getAsString(InventoryEntry.COLUMN_SUPPLIER_NAME);
        if (supplierName == null) {
            throw new IllegalArgumentException("Supplier Name is required");
        }
        Integer supplierPhone = values.getAsInteger(InventoryEntry.COLUMN_SUPPLIER_PHONE);
        if (supplierPhone != null && supplierPhone < 0) {
            throw new IllegalArgumentException("Supplier Phone Number must be valid");
        }
        //Get the Writable Database
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // Insert the new product with the given values
        long id = database.insert(InventoryEntry.TABLE_NAME, null, values);

        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        //Notify all listeners that the data has changed for the boutique content URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return updateProduct(uri, contentValues, selection, selectionArgs);
            case PRODUCT_ID:
                // For the PRODUCT_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateProduct(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    //Update products in the database with the given content values. Apply the changes to the rows
    //specified in the selection and selection arguments (which could be 0 or 1 or more products).
    //Return the number of rows that were successfully updated.
    private int updateProduct(Uri uri, ContentValues values, String selection,
                              String[] selectionArgs) {

        // If the InventoryEntry#COLUMN_PRODUCT_NAME key is present,
        // check that the name value is not null.
        if (values.containsKey(InventoryEntry.COLUMN_PRODUCT_NAME)) {
            String name = values.getAsString(InventoryEntry.COLUMN_PRODUCT_NAME);
            if (name == null) {
                throw new IllegalArgumentException("Product name is required");
            }
        }

        // If the InventoryEntry#COLUMN_PRICE key is present,
        // check that price value is valid.
        if (values.containsKey(InventoryEntry.COLUMN_PRICE)) {
            Float price = values.getAsFloat(InventoryEntry.COLUMN_PRICE);
            if (price != null && price < 0) {
                throw new IllegalArgumentException("Product name is required");
            }
        }

        // If the InventoryEntry#COLUMN_Qunatity key is present,
        // check that quantity value is valid.
        if (values.containsKey(InventoryEntry.COLUMN_QUANTITY)) {
            Integer quantity = values.getAsInteger(InventoryEntry.COLUMN_QUANTITY);
            if (quantity != null && quantity < 0) {
                throw new IllegalArgumentException("Product requires a valid quantity");
            }
        }

        // If the InventoryEntry#COLUMN_SUPPLIER_NAME key is present,
        // check that the supplier name value is not null.
        if (values.containsKey(InventoryEntry.COLUMN_SUPPLIER_NAME)) {
            String supplierName = values.getAsString(InventoryEntry.COLUMN_SUPPLIER_NAME);
            if (supplierName == null) {
                throw new IllegalArgumentException("Supplier name is required");
            }
        }

        // If the InventoryEntry#COLUMN_SUPPLIER_PHONE key is present,
        // check that phone # value is valid.
        if (values.containsKey(InventoryEntry.COLUMN_SUPPLIER_PHONE)) {
            Integer supplierPhone = values.getAsInteger(InventoryEntry.COLUMN_SUPPLIER_PHONE);
            if (supplierPhone != null && supplierPhone < 0) {
                throw new IllegalArgumentException("Supplier Phone must be  valid.");
            }
        }

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, getwriteable database to update the data
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        //Perform the update on the database and get the number of rows affected.
        int rowsUpdated = database.update(InventoryEntry.TABLE_NAME, values,
                selection, selectionArgs);

        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        //Return the number of rows updated.
        return rowsUpdated;

    }//end updateProduct

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        // Getwriteable database
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        //Track the number of rows deleted
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(InventoryEntry.TABLE_NAME, selection,
                        selectionArgs);
                break;
            case PRODUCT_ID:
                // Delete a single row given by the ID in the URI
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(InventoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        // If 1 or more rows were deleted, then notify all listeners that the data at the
        //given URI has changed
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    //This method  is to return a data type MIME for the URI
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PRODUCTS:
                return InventoryEntry.CONTENT_LIST_TYPE;
            case PRODUCT_ID:
                return InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}
