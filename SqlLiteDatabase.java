package bluesky.amthucdanang.custom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bluesky.amthucdanang.entity.QuanAn;

/**
 * Created by manhnc on 20/05/2015.
 */
public class SqlLiteDatabase extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String QUANAN = "QUANAN";

    // address table name
    private static final String DIACHI = "DIACHI";

    // address Table Columns names
    private static final String QUANANID = "quanAnId";
    private static final String TENQUANAN = "tenQuanAn";

    private static final String ADDRESS = "address";

    private static final String PHONE = "PHONE";

    private static final String EMAIL = "EMAIL";

    private static final String ANH = "ANH";

    private static final String GIOMOCUA = "GIOMOCUA";

    private static final String DATETIMES = "DATETIMES";

    public SqlLiteDatabase(Context context) {
        super(context, QUANAN, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CURRENTADDRESS_TABLE = " CREATE TABLE " + DIACHI + "("
                + QUANANID + " TEXT,"
                + TENQUANAN + " TEXT,"
                + ADDRESS + " TEXT,"
                + PHONE + " TEXT,"
                + EMAIL + " TEXT,"
                + ANH + " TEXT,"
                + GIOMOCUA + " TEXT,"
                + DATETIMES + " TEXT"+
                ")";
        db.execSQL(CREATE_CURRENTADDRESS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
/*
        db.execSQL("DROP TABLE "+ DIACHI);
*/

    }

    public void addAddress(QuanAn quanAn) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(QUANANID, quanAn.getQuanAnId());
        values.put(TENQUANAN, quanAn.getTenQuanAn()); // quanAnId
        values.put(ADDRESS, quanAn.getAddress()); // Time
        values.put(PHONE, quanAn.getPhone()); // Time
        values.put(EMAIL, quanAn.getEmail()); // Time
        values.put(ANH, quanAn.getAnh()); // Time
        values.put(GIOMOCUA, quanAn.getGiomocua()); // Time
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        values.put(DATETIMES,currentDateTimeString);

        // Inserting Row
        db.insert(DIACHI, null, values);
        db.close(); // Closing database connection
    }

    public boolean checkAddress(String quanAnId) {
        // Select Query
        String selectQuery = "SELECT  * FROM " + DIACHI + " Where quanAnId = '"+quanAnId+"'";

        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            result = true;
        }

        return result;
    }

    public ArrayList<QuanAn> getCurrentAddress() {
        // Select Query
        String selectQuery = "SELECT  * FROM " + DIACHI +" ORDER BY DATETIMES DESC";
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<QuanAn> list = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                QuanAn quanAn = new QuanAn();
                quanAn.setQuanAnId(cursor.getString(0));
                quanAn.setTenQuanAn(cursor.getString(1));
                quanAn.setAddress(cursor.getString(2));
                quanAn.setPhone(cursor.getString(3));
                quanAn.setEmail(cursor.getString(4));
                quanAn.setAnh(cursor.getString(5));
                quanAn.setGiomocua(cursor.getString(6));
                System.out.println(cursor.getString(7));
                list.add(quanAn);
            } while (cursor.moveToNext());
        }

        return list;
    }

    // Updating single contact
    public int updateAddress(QuanAn quanAn) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(QUANANID, quanAn.getQuanAnId());
        values.put(TENQUANAN, quanAn.getTenQuanAn()); // quanAnId
        values.put(ADDRESS, quanAn.getAddress()); // Time
        values.put(PHONE, quanAn.getPhone()); // Time
        values.put(EMAIL, quanAn.getEmail()); // Time
        values.put(ANH, quanAn.getAnh()); // Time
        values.put(GIOMOCUA, quanAn.getGiomocua()); // Time
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        values.put(DATETIMES,currentDateTimeString);

        // updating row
        return db.update(DIACHI, values, QUANANID + " = ?",
                new String[]{String.valueOf(quanAn.getQuanAnId())});
    }
}
