package com.jldroid25.android.boutique.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jldroid25.android.boutique.data.BoutiqueContract.InventoryEntry;

//Database helper for Boutique app. Manages database creation and version management.
public class BoutiqueDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = BoutiqueDbHelper.class.getSimpleName();

    //increment the database version by 1 If new changes were made from first version.
    // CONSTANTS FOR Database name & version
    public static final String DATABASE_NAME = "boutique.db";

    public static final int DATABASE_VERSION = 1;

    //The class Constructor
    public BoutiqueDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Implements the  abstract Override method the onCreate method
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String Constant SQL_CREATE_INVENTORY_TABLE,
        // that contains the SQL statement to create the Boutique Inventory table
        String SQL_CREATE_BOUTIQUE_TABLE = "CREATE TABLE " + BoutiqueContract.InventoryEntry.TABLE_NAME + " ("
                + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COLUMN_PRICE + " FLOAT NOT NULL,"
                + InventoryEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, "
                + InventoryEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL,"
                + InventoryEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_BOUTIQUE_TABLE);
        Log.v(LOG_TAG, "Database table was created with no issues");
    }

    //Implements the abstract Override onUpgrade() method,
    // this is called when database needs to be updated.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
} // end class