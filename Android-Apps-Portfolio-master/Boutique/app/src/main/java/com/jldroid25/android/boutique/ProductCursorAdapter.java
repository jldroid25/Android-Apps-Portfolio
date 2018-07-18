package com.jldroid25.android.boutique;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.jldroid25.android.boutique.data.BoutiqueContract.InventoryEntry;

//is an adapter for a list that uses a Cursor of product data as its data source.
//This adapter knows how to create list items for each row of product data in the Cursor.
public class ProductCursorAdapter extends CursorAdapter {

    //For updating the Quantity Field in the view
    int productQtyInteger;
    TextView productQuantity;

    //Constructor with 0 flag
    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    //Makes a new blank list item view. No data is set (or bound) to the views yet.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item,
                parent, false);
    }

    /*
    This method binds the product data (in the current row pointed to by cursor) to the given
    * list item layout. For example, the product name for the current product can be
    * set on the name TextView in the list item layout.
    */
    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        // Find individual views that we want to modify in the list item layout

        TextView productCategory = view.findViewById(R.id.prod_info);
        TextView productTxtViewName = view.findViewById(R.id.prodName);
        TextView productTxtViewPrice = view.findViewById(R.id.prodPrice);
        final TextView productTxtViewQuantity = view.findViewById(R.id.prodQuantity);

        //Find the column of the product attributes that we're interested in.
        final int nameColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryEntry.COLUMN_QUANTITY);

        //Read the product attributes from the Cursor for the current product
        String productName = cursor.getString(nameColumnIndex);
        String productPrice = cursor.getString(priceColumnIndex);
        String productQty = cursor.getString(quantityColumnIndex);

        //Update the TextView with the attributes for the current products.
        productCategory.setText(R.string.category_overview);
        productTxtViewName.setText(productName);
        productTxtViewPrice.setText(productPrice);
        productTxtViewQuantity.setText(productQty);

        //Our TextView to display data
        productQuantity = view.findViewById(R.id.prodQuantity);

        //Sales btn click
        Button saleBtn = view.findViewById(R.id.sales_btn);

        //Get the current Item(s) Id
        int currentProductId = cursor.getInt(cursor.getColumnIndex(InventoryEntry._ID));

        //Make the content URI for the current Product Id
        final Uri contentUri = Uri.withAppendedPath(InventoryEntry.CONTENT_URI,
                Integer.toString(currentProductId));

        //Onclick for Button for Sales Btn to reduce quantity by 1
        saleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productQtyInteger = Integer.valueOf(productTxtViewQuantity.getText().toString());

                if (productQtyInteger > 0) {
                    productQtyInteger -= 1;
                }
                //Content value to update the product Quantity
                ContentValues values = new ContentValues();
                values.put(InventoryEntry.COLUMN_QUANTITY, productQtyInteger);

                //Update the database
                context.getContentResolver().update(contentUri, values, null, null);

                //Let's notify Changes in the adapter
                notifyDataSetChanged();
            } //end onClick
        });

    } //end bindView
}//Class
