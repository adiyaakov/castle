package com.example.adi.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adi on 09/08/2016.
 */
public class DataOpenHelper extends SQLiteOpenHelper {
    private static final String DB_FILE = "dbo.db";
    private static final int DB_VERSION = 1;


    public static final String TABLE_ITEMS = "items";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CATAGORY = "catagory";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ITEM_PRICE = "price";
    public static final String COLUMN_IMG_LOCATION = "img_location";

    //+ " INTEGER, "


    private static final String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_DESCRIPTION + " TEXT, " +
            COLUMN_ITEM_PRICE + " TEXT, " +
            COLUMN_CATAGORY + " TEXT" +
            ");";


    //COLUMN_IMG_LOCATION + " TEXT" +




    public DataOpenHelper(Context context) {
        super(context, DB_FILE , null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ITEMS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean updateDataRow(String id, String name, String description, String price, String catagory, String img_location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DataOpenHelper.COLUMN_NAME, name);
        values.put(DataOpenHelper.COLUMN_DESCRIPTION, description);
        values.put(DataOpenHelper.COLUMN_ITEM_PRICE, price);
        values.put(DataOpenHelper.COLUMN_CATAGORY, catagory);
        //-- DO NOT LET ACCESS TO IMG NEED PASSWORD CODE
        //values.put(DataOpenHelper.COLUMN_IMG_LOCATION,catagory);

        db.update(DataOpenHelper.TABLE_ITEMS,values,COLUMN_ID+"=?", new String[] {id});
        db.close();
        return true;
    }
    public void deleteDataRow(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataOpenHelper.TABLE_ITEMS,DataOpenHelper.COLUMN_ID + "=?",new String[]{id});
        db.close();
    }
}


