package com.jldroid25.android.boutique.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class BoutiqueContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private BoutiqueContract() {
    }

    //URI Constant to help identify Content_Authority set up in manifest file
    // This contains the schema for our CONTENT_AUTHORITY.
    public static final String CONTENT_AUTHORITY = "com.jldroid25.android.boutique";

    //Let set up a BASE_CONTENT_URI concat with CONTENT_AUTHORITY
    // to share every URI associated with BoutiqueContract.
    //We use the parse() to make this a usable URI, which takes a URI string & return a Uri.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //This constants stores the path for each of the tables
    // which will be appended to the base content URI.
    public static final String PATH_PRODUCTS = "boutique";

    //Create inner class that defined Table contents
    public static class InventoryEntry implements BaseColumns {

        //Lets create the full Uri for the ProductEntry  as a constant to the path segment.
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);

        //The MIME type of the Number CONTENT_URI for a list of products.
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        //The MIME type of the number CONTENT_URI for a single product.
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;

        //Create the String column names constants
        public static final String TABLE_NAME = "boutique";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "ProductName";
        public static final String COLUMN_PRICE = "Price";
        public static final String COLUMN_QUANTITY = "Quantity";
        public static final String COLUMN_SUPPLIER_NAME = "SupplierName";
        public static final String COLUMN_SUPPLIER_PHONE = "SupplierPhoneNumber";
    } // end InventoryEntry
} //End BoutiqueContract class
