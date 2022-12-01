package com.example.myapplication.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseHandler extends SQLiteOpenHelper {
    // static variables
    // database version
    private static final int DATABASE_VERSION = 1;
    // database name
    private static final String DATABASE_NAME = "test99.db";

    // table name contacts
    private static final String TABLE_Users = "users";

    // contacts table Column names
    private static final String USER_KEY_ID = "id";
    private static final String USER_KEY_USERNAME = "username";
    private static final String USER_KEY_EMAIL = "email";
    private static final String USER_KEY_PASSWORD = "password";
    private static final String USER_KEY_REPASSWORD = "repassword";
    private static final String USER_KEY_ROLE = "role";



    public UserDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // creating the contacts table
    @Override
    public void onCreate(SQLiteDatabase db) {
        // query for create table

        String CREATE_USERS_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_Users+"("+USER_KEY_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+USER_KEY_USERNAME+" TEXT, "+USER_KEY_EMAIL+" TEXT, "+USER_KEY_PASSWORD+" TEXT, "+USER_KEY_REPASSWORD+" TEXT, "+USER_KEY_ROLE    +" TEXT)";
        db.execSQL(CREATE_USERS_TABLE);
    }

    // upgrading the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_Users);
        //creating table again
        onCreate(db);
    }

    /**
     * Create, Read, Update, Delete Operations
     */


    //check methods

    public Boolean checkmail (String email) {

        SQLiteDatabase mydb=this.getWritableDatabase();
        Cursor cursor=mydb.rawQuery("Select * from Users where email =?",new String[] {email});
        if(cursor.getCount()>0)
                return true ;
        else
                return false ;
    }

    public Boolean checkmailpassword (String email,String password) {

        SQLiteDatabase mydb=this.getWritableDatabase();
        Cursor cursor=mydb.rawQuery("Select * from Users where email =? and password = ? ",new String[] {email,password});
        if(cursor.getCount()>0)
            return true ;
        else
            return false ;
    }

    public String getrole (String email, String password) {
        String result = "";
        SQLiteDatabase mydb=this.getWritableDatabase();
        Cursor cursor=mydb.rawQuery("Select role from doctors where email =? and password = ? ",new String[] {email,password});
        if (cursor.moveToFirst()) result = cursor.getString(0);
        cursor.close();
        mydb.close();
        return result;

    }


    //adding a new user
    public void addUser(User user){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_KEY_USERNAME,user.getUsername());
        values.put(USER_KEY_EMAIL,user.getEmail());
        values.put(USER_KEY_PASSWORD,user.getPassword());
        values.put(USER_KEY_REPASSWORD,user.getRepassword());
        values.put(USER_KEY_ROLE,user.getRole());


        // inserting row
        db.insert(TABLE_Users,null,values);
        db.close();

    }

    //getting a single contact
    User getSpeciality(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_Users,new String[]{USER_KEY_ID,USER_KEY_USERNAME,USER_KEY_EMAIL,USER_KEY_PASSWORD,USER_KEY_REPASSWORD,USER_KEY_ROLE},
                USER_KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        User user = new User();
        return user;
    }

    // getting all doctors
    public List<User> getAllDoctors(){
        List<User> specialityList=new ArrayList<User>();
        // select all query
        String selectQuery="SELECT * FROM "+ TABLE_Users;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping to all rows for adding it to list
        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUsername((cursor.getString(1)));
                user.setEmail((cursor.getString(2)));
                user.setPassword((cursor.getString(3)));
                user.setRepassword((cursor.getString(4)));
                user.setRole((cursor.getString(5)));



                // add contacts to list
                specialityList.add(user);



            }while (cursor.moveToNext());
        }
        return specialityList;
    }

    // update single contact
    public int updateSpeciality(User  user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_KEY_USERNAME,user.getUsername());
        values.put(USER_KEY_EMAIL,user.getEmail());
        values.put(USER_KEY_PASSWORD,user.getPassword());
        values.put(USER_KEY_REPASSWORD,user.getRepassword());
        values.put(USER_KEY_ROLE,user.getRole());

        //updating row
        return db.update(TABLE_Users,values,USER_KEY_ID+"= ?",new String[]{String.valueOf(user.getId())});


    }

    //delete the single contact
    public void deleteSpeciality(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users,USER_KEY_ID+"= ?",new String[]{String.valueOf(user.getId())});
        db.close();

    }

    // getting contacts count
    public int getUsersCount(){
        String countQuery = "SELECT * FROM "+ TABLE_Users;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.rawQuery(countQuery,null);
        cursor.close();
        return  cursor.getCount();

    }

}