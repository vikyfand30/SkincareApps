package com.jefaskincare.mobile.android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Jefa.db";
    public static final String TABLE_NAME = "Cart";
    public static final String ITEM_COLUMN_ID = "itemId";
    public static final String ITEM_COLUMN_NAME = "name";
    public static final String ITEM_COLUMN_PIN = "pin";
    private HashMap map;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table Cart " +
                        "(id Integer primary key autoincrement," +
                        " productid String," +
                        " userid String," +
                        " price String," +
                        " qty Integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Cart");
        onCreate(sqLiteDatabase);
    }

    public boolean insertCart(String productid,
                              String userid,
                              String price,
                              Integer qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("productid", productid);
        contentValues.put("userid", userid);
        contentValues.put("price", price);
        contentValues.put("qty", qty);
        db.insert("Cart", null, contentValues);

        return true;
    }

    public Cursor getData(String productid, String userid) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select sum(qty) from Cart where productid = '"+ productid + "' and userid = '"+ userid +"'";
        Cursor res = null;
        if (checkDB(query)){
            res = db.rawQuery(query, null);
        }
        return res;
    }

    public Cursor getDataAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select sum(qty) from Cart ";
        Cursor res = null;
        if (checkDB(query)){
            res = db.rawQuery(query, null);
        }
        return res;
    }

    public Boolean checkDB(String query){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(query, null);
        if (res != null)
        {
            return true;
            /* record exist */
        }
        else
        {
            return false;
            /* record not exist */
        }
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public Boolean deleteDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(DBHelper.TABLE_NAME, null, null);
        return true;
    }

}

