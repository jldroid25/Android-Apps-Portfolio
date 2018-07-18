package com.jldroid25.android.boutique;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.jldroid25.android.boutique.data.BoutiqueContract.InventoryEntry;


//Class to Allow user to create a new product
public class BoutiqueEditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String LOG_TAG = BoutiqueEditorActivity.class.getSimpleName();

    //ButterKnife to inject views in our Activity rather than using findViewById

    //EditText field to enter the product's name
    @BindView(R.id.edit_product_name)
    EditText productNameEditText;

    // EditText field to enter the product's price
    @BindView(R.id.edit_product_price)
    EditText productPriceEditText;

    //EditText field to enter the product's quantity
    @BindView(R.id.edit_product_quantity)
    EditText productQuantityEditText;

    // EditText field to enter the supplier's name
    @BindView(R.id.edit_supplier_name)
    EditText supplierName;

    //EditText field to enter the supplier's phone number
    @BindView(R.id.edit_supplier_phone)
    EditText supplierPhone;

    //For the Order button
    @BindView(R.id.order_btn)
    Button orderBtn;

    //For the Increase Quantity button
    @BindView(R.id.qty_plus_btn)
    Button plusBtn;

    //For the Decrease Quantity button
    @BindView(R.id.qty_minus_btn)
    Button minusBtn;

    //for the Delete Button
    @BindView(R.id.del_btn)
    Button deleteButton;

    //Identifier for the product data loader
    private static final int EXISTING_PRODUCT_LOADER = 0;

    //Content URI for the existing product (null if it's a new product)
    private Uri currentProductUri;

    //Boolean flag that keeps track of whether the product
    // has been edited (true) or not (false)
    private boolean productHasChanged = false;

    //OnTouchListener that listens for any user touches on a View, implying that they
    // are modifying the view, & we change the productHasChanged boolean to true.
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            productHasChanged = true;
            return false;
        }
    };

    //For updating the EditText Field in the view
    int prodIncrease = 0;

    //perform an update on the existing product increment values by 1
    private void upgradeProduct() {

        //Parse the in EditText as an integer & stores it in this int variable
        int productQuantity = Integer.parseInt(productQuantityEditText.getText().toString().trim());

        // Only perform the update if this is an existing product.
        if (currentProductUri != null) {
            prodIncrease = productQuantity + 1;
        } else {
            int rowUpdated = getContentResolver().update(currentProductUri,
                    null, null, null);

            if (rowUpdated == 0) {
                // If no rows were affected, then there was an error with the update.
                Toast.makeText(this, getString(R.string.editor_update_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the update was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_update_product_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    } // end upgradeProduct()

    //perform an update on the existing product decrement values by 1
    private void downgradeProduct() {

        int productQuantity = Integer.parseInt(productQuantityEditText.getText().toString().trim());

        // Only perform the update if this is an existing product.
        if (currentProductUri != null) {
            if (productQuantity >= 2) {
                prodIncrease = productQuantity - 1;
            } else if (productQuantity <= 1) {
                prodIncrease = productQuantity - 0;

                Toast.makeText(this, getString(R.string.qty_msg_dec),
                        Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            int rowUpdated = getContentResolver().update(currentProductUri,
                    null, null, null);
            if (rowUpdated == 0) {
                // If no rows were affected, then there was an error with the update.
                Toast.makeText(this, getString(R.string.editor_update_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the update was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_update_product_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    } // end downgradeProduct()

    //Update  the result for Product Quantity by 1
    public void displayProductInc(int scoreUp) {
        TextView up_prod_qty = findViewById(R.id.edit_product_quantity);
        up_prod_qty.setText(String.valueOf(scoreUp));
    }

    //Update the result for tea
    public void displayProductDec(int scoreUp) {
        TextView down_prod_qty = findViewById(R.id.edit_product_quantity);
        down_prod_qty.setText(String.valueOf(scoreUp));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        //Use Butter knife Library to bind our views
        ButterKnife.bind(this);

        // Examine the intent that was used to launch this activity,
        // in order to figure out if we're creating a new product or editing an existing one.
        Intent intent = getIntent();
        currentProductUri = intent.getData();

        // If the intent DOES NOT contain a product content URI,
        // then we know that we are creating a new product.
        if (currentProductUri == null) {
            // This is a new product, so change the app bar to say "Add a Product"
            setTitle(getString(R.string.editor_activity_title_new_product));

            // Invalidate the options menu, so the "Delete" menu option can be hidden.
            // (It doesn't make sense to delete a product that hasn't been created yet.)
            invalidateOptionsMenu();

            //Since we're adding new product Disable the Order, Inc, dec, & Del buttons
            orderBtn.setEnabled(false);
            plusBtn.setEnabled(false);
            minusBtn.setEnabled(false);
            deleteButton.setEnabled(false);

        } else {
            // Otherwise this is an existing product, so change app bar to say "Edit Product"
            setTitle(getString(R.string.editor_activity_title_edit_product));
            // Initialize a loader to read the product data from the database
            // and display the current values in the editor
            getLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this);
        }

        // Setup OnTouchListeners on all the input fields, so we can determine if the user
        // has touched or modified them. This will let us know if there are unsaved changes
        // or not, if the user tries to leave the editor without saving.
        productNameEditText.setOnTouchListener(touchListener);
        productPriceEditText.setOnTouchListener(touchListener);
        productQuantityEditText.setOnTouchListener(touchListener);
        supplierName.setOnTouchListener(touchListener);
        supplierPhone.setOnTouchListener(touchListener);


        //For the plus Quantity button
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upgradeProduct();
                displayProductInc(prodIncrease);
            }
        });

        //For the Minus Quantity Button
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downgradeProduct();
                displayProductDec(prodIncrease);
            }
        });

        //For the Order button to call Supplier's phone number
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String dialSupplier = supplierPhone.getText().toString().trim();
                    Intent orderProd = new Intent(Intent.ACTION_DIAL);
                    String temp = "tel:" + dialSupplier;
                    orderProd.setData(Uri.parse(temp));
                    startActivity(orderProd);
                } catch (ActivityNotFoundException activityException) {
                    Log.v(LOG_TAG, "Dialing Supplier Phone # From EditText Failed");
                }
            }
        });

        //For the Delete button to purge the current product in Product Detail view
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pop up confirmation dialog for deletion
                showDeleteConfirmationDialog();
            }
        });

    } //end on Create

    //Get the user input from editor and save new product(s) into database
    private void saveProduct() {
        //Get the data from the EditText field, using the defined variables above.
        String productNameString = productNameEditText.getText().toString().trim();
        String productPriceString = productPriceEditText.getText().toString().trim();
        String productQuantityString = productQuantityEditText.getText().toString().trim();
        String supplierNameString = supplierName.getText().toString().trim();
        String supplierPhoneString = supplierPhone.getText().toString().trim();


        //Check if this is supposed to be a new product
        //and check if all the fields in the editor are blank
        if (currentProductUri == null && TextUtils.isEmpty(productNameString)) {
            Toast.makeText(this, getString(R.string.required_prod_name),
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), BoutiqueEditorActivity.class);
            startActivity(i);
            return;

        } else if (currentProductUri == null && TextUtils.isEmpty(productPriceString)) {
            Toast.makeText(this, getString(R.string.required_prod_price),
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), BoutiqueEditorActivity.class);
            startActivity(i);
            return;
        } else if (currentProductUri == null && TextUtils.isEmpty(productQuantityString)) {
            Toast.makeText(this, getString(R.string.required_prod_qty),
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), BoutiqueEditorActivity.class);
            startActivity(i);
            return;
        } else if (currentProductUri == null && TextUtils.isEmpty(supplierNameString)) {
            Toast.makeText(this, getString(R.string.required_supplier_name),
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), BoutiqueEditorActivity.class);
            startActivity(i);
            return;

        } else if (currentProductUri == null && TextUtils.isEmpty(supplierPhoneString)) {
            Toast.makeText(this, getString(R.string.required_supplier_phone),
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), BoutiqueEditorActivity.class);
            startActivity(i);
            return;
        }

        //Create a ContentValues object where column names are the keys,
        // and product attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COLUMN_PRODUCT_NAME, productNameString);
        values.put(InventoryEntry.COLUMN_PRICE, productPriceString);
        values.put(InventoryEntry.COLUMN_QUANTITY, productQuantityString);
        values.put(InventoryEntry.COLUMN_SUPPLIER_NAME, supplierNameString);
        values.put(InventoryEntry.COLUMN_SUPPLIER_PHONE, supplierPhoneString);


        //check if this is a new or existing product by checking
        // if currentProductUri is null or not
        if (currentProductUri == null) {
            // This is a NEW product, so insert a new product into the provider,
            // returning the content URI for the new product.
            Uri newUri = getContentResolver().insert(InventoryEntry.CONTENT_URI, values);

            // Show a toast message depending on whether or not the insertion was successful.
            if (newUri == null) {
                Log.v(LOG_TAG, "Failed to insert Product into database from editor activity");

                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, getString(R.string.editor_insert_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Log.v(LOG_TAG, "Product(s) was successfully entered into database");
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_insert_product_successful),
                        Toast.LENGTH_SHORT).show();
            }
        } else {

            // Otherwise this is an EXISTING product, so update the product with content
            // URI:currentProductUri and pass in the new ContentValues.
            // Pass in null for the selection and selection args because currentProductUri will
            // already identify the correct row in the database that we want to modify.
            int rowsAffected = getContentResolver().update(currentProductUri,
                    values, null, null);

            // Show a toast message depending on whether or not the update was successful.
            if (rowsAffected == 0) {
                Log.v(LOG_TAG, "Failed to update Product into database from editor activity");
                // If no rows were affected, then there was an error with the update.
                Toast.makeText(this, getString(R.string.editor_update_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Log.v(LOG_TAG, "Product(s) was successfully update into database");
                // Otherwise, the update was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_update_product_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    } // end saveProduct()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {

            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // When save button is click, add the data to db
                saveProduct();
                //Exit the Activity & jump back to Boutique Activity
                finish();
                return true;

            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // If the Product wasn't change navigate back to parent activity (BoutiqueActivity)
                if (!productHasChanged) {
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
                }

                // Otherwise if there are unsaved changes, setup a dialog to warn the user.
                // Create a click listener to handle the user confirming that
                // changes should be discarded.
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(BoutiqueEditorActivity.this);
                            }
                        };
                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //This method is called when the back button is pressed.
    @Override
    public void onBackPressed() {
        // If the product hasn't changed, continue with handling back button press
        if (!productHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    } //end onBackPress

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Since the editor shows all product attributes, define a projection that contains
        // all columns from the boutique table
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COLUMN_PRODUCT_NAME,
                InventoryEntry.COLUMN_PRICE,
                InventoryEntry.COLUMN_QUANTITY,
                InventoryEntry.COLUMN_SUPPLIER_NAME,
                InventoryEntry.COLUMN_SUPPLIER_PHONE};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                currentProductUri,         // Query the content URI for the current product
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);

    } //end onCreateLoader

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {
            // Find the columns of product attributes that we're interested in
            int productNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
            int productPriceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_SUPPLIER_PHONE);

            // Extract out the value from the Cursor for the given column index
            String currentProductName = cursor.getString(productNameColumnIndex);
            Float currentProductPrice = cursor.getFloat(productPriceColumnIndex);
            int currentProductQuantity = cursor.getInt(quantityColumnIndex);
            String currentSupplierName = cursor.getString(supplierNameColumnIndex);
            String currentSupplierPhone = cursor.getString(supplierPhoneColumnIndex);

            // Update the views on the screen with the values from the database
            productNameEditText.setText(currentProductName);
            productPriceEditText.setText(Float.toString(currentProductPrice));
            productQuantityEditText.setText(Integer.toString(currentProductQuantity));
            supplierName.setText(currentSupplierName);
            supplierPhone.setText(currentSupplierPhone);
        }
    } //end onLoadFinished

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        productNameEditText.setText("");
        productPriceEditText.setText("");
        productQuantityEditText.setText("");
        supplierName.setText("");
        supplierPhone.setText("");
    } //onLoaderReset

    /**
     * Show a dialog that warns the user there are unsaved changes that will be lost
     * if they continue leaving the editor.
     *
     * @param discardButtonClickListener is the click listener for what to do when
     *                                   the user confirms they want to discard their changes
     */
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {

        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the product.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Prompt the user to confirm that they want to delete this product.
    private void showDeleteConfirmationDialog() {

        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the positive and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the product.
                deleteProduct();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the product.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Perform the deletion of the product in the database.
    private void deleteProduct() {
        // Only perform the delete if this is an existing product.
        if (currentProductUri != null) {

            // Call the ContentResolver to delete the product at the given content URI.
            // Pass in null for the selection and selection args because the currentProductUri
            // content URI already identifies the product that we want.
            int rowsDeleted = getContentResolver().delete(currentProductUri, null,
                    null);

            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                Log.v(LOG_TAG, "Failed to delete Product from database");

                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.editor_delete_product_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Log.v(LOG_TAG, "Had successfully delete the Product from the database");
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_delete_product_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
        // Close the activity
        finish();
    }
} //end class