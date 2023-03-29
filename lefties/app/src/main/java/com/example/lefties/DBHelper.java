package com.example.lefties;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    final static  String  DATABASE_NAME = "Lefties.db";
    final static int DATABASE_VERSION = 4;


    // TABLE 1: Account_Table
    //Raiyan-added password field
    final static String TABLE1_NAME = "account_table";
    final static String T1COL_1 = "account_Id";
    final static String T1COL_2 = "account_name";
    final static String T1COL_3 = "account_type";
    final static String T1COL_4 = "account_email";
    final static String T1COL_5 = "account_password";
    final static String T1COL_6 = "account_phone";
    final static String T1COL_7 = "account_address";
    final static String T1COL_8 = "account_city";


    //TABLE 2 : Restaurant
    final static String TABLE2_NAME = "restaurant_table";
    final static String T2COL_1 = "restaurant_Id";
    final static String T2COL_2 = "restaurant_type";
    final static String T2COL_3 = "account_Id";

    //TABLE 3: Food Table

    final static String TABLE3_NAME = "food_table";
    final static String T3COL_1 = "food_Id";
    final static String T3COL_2 = "account_Id";  //FK
    final static String T3COL_3 = "food_name";
    final static String T3COL_4 = "food_discount_price";
    final static String T3COL_5 = "food_regular_price";
    final static String T3COL_6 = "food_qty";

    //Table 4 : Order Table

    final static String TABLE4_NAME = "order_table";
    final static String T4COL_1 = "order_Id";
    final static String T4COL_2 = "order_status";  //FK
    final static String T4COL_3 = "order_date";
    final static String T4COL_4 = "order_type";
    final static String T4COL_5 = "order_total";


    //Table 5 : Cart Table
    final static String TABLE5_NAME = "cart_table";
    final static String T5COL_1 = "cart_Id";  //PK
    final static String T5COL_2 = "order_Id";  //FK
    final static String T5COL_3 = "food_Id";  //FK
    final static String T5COL_4 = "food_Qty_Ordered";
    final static String T5COL_5 = "checkout_status";



    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase database =this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Raiyan-added Password field, changed name from query1 to queryAccountTable
        String queryAccountTable = "CREATE TABLE " + TABLE1_NAME + "(" + T1COL_1 +
                " INTEGER PRIMARY KEY, " + T1COL_2 + " TEXT, " + T1COL_3 + " TEXT, " +
                T1COL_4 + " TEXT, "+ T1COL_5 + " TEXT, " + T1COL_6 + " TEXT, " + T1COL_7 + " TEXT, " + T1COL_8 + " TEXT)";


        sqLiteDatabase.execSQL(queryAccountTable);

        // Restaurant
        String query1 = "CREATE TABLE " + TABLE2_NAME + "(" + T2COL_1 +
                " INTEGER PRIMARY KEY, " + T2COL_2 + " TEXT," +  T2COL_3 + " TEXT)";

        sqLiteDatabase.execSQL(query1);

        // Food
        String query2 = "CREATE TABLE " + TABLE3_NAME + "(" + T3COL_1 +
                " INTEGER PRIMARY KEY, "  + T3COL_2 + " TEXT, " +
                T3COL_3 + " TEXT, "+ T3COL_4 + " REAL, " + T3COL_5 + " REAL, " + T3COL_6 + " INTEGER)";

        sqLiteDatabase.execSQL(query2);

        // Order
        String query3 = "CREATE TABLE " + TABLE4_NAME + "(" + T4COL_1 +
                " INTEGER PRIMARY KEY, "  + T4COL_2 + " TEXT, " +
                T4COL_3 + " TEXT, "+ T4COL_4 + " TEXT, "+ T4COL_5 + " TEXT)";

        sqLiteDatabase.execSQL(query3);

        // Cart
        String query4 = "CREATE TABLE " + TABLE5_NAME + "(" + T5COL_1 +
                " INTEGER PRIMARY KEY, "  + T5COL_2 + " TEXT, " +
                T5COL_3 + " TEXT, " +
                T5COL_4 + " TEXT, " + T5COL_5 + " TEXT)";

        sqLiteDatabase.execSQL(query4);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE4_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE5_NAME);

        onCreate(sqLiteDatabase);

    }


    //Adding for account table
    //Minor Changes made by Raiyan, removing aid, it should be generated automatically
    //To see the original one look below
    public boolean addAccount(String aname, String atype, String aemail, String apassword, String aphone,
                              String aaddress, String acity)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL_2, aname);
        values.put(T1COL_3, atype);
        values.put(T1COL_4, aemail);
        values.put(T1COL_5, apassword);
        values.put(T1COL_6, aphone);
        values.put(T1COL_7, aaddress);
        values.put(T1COL_8, acity);

        long l = sqLiteDatabase.insert(TABLE1_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;

    }

    //Original addAccount
    /*
    public boolean addAccount(String aid, String aname, String atype, String aemail, String aphone,
                              String aaddress, String acity)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL_2, aname);
        values.put(T1COL_3, atype);
        values.put(T1COL_4, aemail);
        values.put(T1COL_5, aphone);
        values.put(T1COL_6, aaddress);
        values.put(T1COL_7, acity);

        long l = sqLiteDatabase.insert(TABLE1_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;

    }*/

    //Adding Restaurant
    public boolean addRestaurant(String Rtype , String aid)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T2COL_2, Rtype);


        long l = sqLiteDatabase.insert(TABLE2_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;

    }

    public void seedRestaurant(){
        //
    }

    //Adding Food
    public boolean addFood(Integer aid, String fname, Double fdiscountprice, Double fregularprice,
                              Integer fqty)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL_2, aid);
        values.put(T3COL_3, fname);
        values.put(T3COL_4, fregularprice);
        values.put(T3COL_5, fdiscountprice);
        values.put(T3COL_6, fqty);

        long l = sqLiteDatabase.insert(TABLE3_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;

    }

    public void seedFoodTable(){
        addFood(1, "Tandoori Chicken", 30.0, 25.0, 3);
        addFood(1, "Bread", 4.0, 10.0, 16);
        addFood(1, "Iced Coffee", 3.0, 8.0, 160);
        addFood(2, "Beef Stir-fry", 30.0, 25.0, 3);
        addFood(2, "Cookies", 4.0, 10.0, 16);

    }

    //Adding cart

    public boolean addOrder(String ostatus, String odate, String otype, String ototal)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(T4COL_2, ostatus);
        values.put(T4COL_3, odate);
        values.put(T4COL_4, otype);
        values.put(T4COL_5, ototal);


        long l = sqLiteDatabase.insert(TABLE4_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;

    }

    //Adding Order

    public boolean addCart(String foodqtyOrd, String checkoutstatus)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T5COL_3, foodqtyOrd);
        values.put(T5COL_4,checkoutstatus);

        long l = sqLiteDatabase.insert(TABLE5_NAME,null,values);
        if(l > 0)
            return true;
        else
            return false;
    }

    // Reading Data

    //Account
    //Raiyan-making changes to viewDataAccount method
    public Cursor viewDataAccount(String userEmail, String userPass){
        SQLiteDatabase database = this.getReadableDatabase();
//        String query = "SELECT * FROM " + TABLE1_NAME;
        String query = "SELECT * FROM " + TABLE1_NAME +
                " WHERE " + T1COL_4 + "=" + '"' + userEmail + '"' + " AND " +
                 T1COL_5 + "=" + '"' + userPass + '"';
        Cursor cursor = database.rawQuery(query,null);
        //String query = "SELECT * FROM " + TABLE1_NAME + " WHERE Id = ?";
        //Cursor cursor = database.rawQuery(query,new String[]{"2"});
        return cursor;
    }

    //Original viewDataAccount
    /* public Cursor viewDataAccount(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME;
        Cursor cursor = database.rawQuery(query,null);
        //String query = "SELECT * FROM " + TABLE1_NAME + " WHERE Id = ?";
        //Cursor cursor = database.rawQuery(query,new String[]{"2"});
        return cursor;
    }*/

    //Restaurant
    public Cursor viewDataRest(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE2_NAME;
        Cursor cursor = database.rawQuery(query,null);
        return cursor;
    }

    //Food
    public Cursor viewDataFood(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE3_NAME;
        Cursor cursor = database.rawQuery(query,null);
        return cursor;
    }

    public Cursor viewDataFoodByRestaurant(int acctId){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE3_NAME
                + " WHERE " + T3COL_2 + "=" + acctId;
        Cursor cursor = database.rawQuery(query,null);
        return cursor;
    }
    //Order
    public Cursor viewDataOrder(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE4_NAME;
        Cursor cursor = database.rawQuery(query,null);
        return cursor;
    }

    //Cart
    public Cursor viewDataCart(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE5_NAME;
        Cursor cursor = database.rawQuery(query,null);
        return cursor;

    }

    //Deleting the data

    //Account

    public boolean deleteRecAccount(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d = sqLiteDatabase.delete(TABLE1_NAME,"account_Id=?",
                new String[]{Integer.toString(id)});
        if(d>0)
            return true;
        else
            return false;
    }

    //Restaurant
    public boolean deleteRecRest(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d = sqLiteDatabase.delete(TABLE2_NAME,"restaurant_Id=?",
                new String[]{Integer.toString(id)});
        if(d>0)
            return true;
        else
            return false;
    }

    //Food
    public boolean deleteRecFood(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d = sqLiteDatabase.delete(TABLE3_NAME,T3COL_1 + "=?",
                new String[]{Integer.toString(id)});
        if(d>0)
            return true;
        else
            return false;
    }

    //Order
    public boolean deleteRecOrder(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d = sqLiteDatabase.delete(TABLE4_NAME,"order_Id=?",
                new String[]{Integer.toString(id)});
        if(d>0)
            return true;
        else
            return false;
    }

    //Cart
    public boolean deleteCart(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d = sqLiteDatabase.delete(TABLE5_NAME,"food_Id=?",
                new String[]{Integer.toString(id)});
        if(d>0)
            return true;
        else
            return false;
    }


    ///Updating the tables

    //updating account type
    public boolean updateRecAccount(int id,String c){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T1COL_3,c);
        int u = sqLiteDatabase.update(TABLE1_NAME,contentValues,"id=?",
                new String[]{Integer.toString(id)});
        if(u>0)
            return true;
        else
            return false;
    }



}
