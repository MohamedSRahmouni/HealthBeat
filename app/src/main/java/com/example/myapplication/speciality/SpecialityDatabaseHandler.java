package com.example.myapplication.speciality;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.User.User;
import com.example.myapplication.doctor.Doctor;

import java.util.ArrayList;
import java.util.List;

public class SpecialityDatabaseHandler extends SQLiteOpenHelper {
    // static variables
    // database version
    private static final int DATABASE_VERSION = 1;
    // database name
    private static final String DATABASE_NAME = "test120.db";

    // table name contacts
    private static final String TABLE_SPECIALITY = "specialities";

    // contacts table Column names
    private static final String SPECIALITY_KEY_ID = "id";
    private static final String SPECIALITY_KEY_NAME = "name";

    // table name contacts
    private static final String TABLE_Doctors = "doctors";

    // contacts table Column names
    private static final String DOCTOR_KEY_ID = "id";
    private static final String DOCTOR_KEY_USERNAME = "username";
    private static final String DOCTOR_KEY_SPECIALITY = "speciality";
    private static final String DOCTOR_KEY_NUMBER = "number";
    private static final String DOCTOR_KEY_EMAIL = "email";
    private static final String DOCTOR_KEY_PASSWORD = "password";
    private static final String DOCTOR_KEY_REPASSWORD = "repassword";
    private static final String DOCTOR_KEY_VERIFIED = "verified";
    // table name contacts
    private static final String TABLE_Users = "users";

    // contacts table Column names
    private static final String USER_KEY_ID = "id";
    private static final String USER_KEY_USERNAME = "username";
    private static final String USER_KEY_EMAIL = "email";
    private static final String USER_KEY_PASSWORD = "password";
    private static final String USER_KEY_REPASSWORD = "repassword";
    private static final String USER_KEY_ROLE = "role";


    public SpecialityDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("constructor");
    }

    // creating the contacts table
    @Override
    public void onCreate(SQLiteDatabase db) {
        // query for create table

        System.out.println("calling on create");
        String CREATE_SPECIALITIES_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_SPECIALITY+"("+SPECIALITY_KEY_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+SPECIALITY_KEY_NAME+" TEXT)";
        String CREATE_DOCTORS_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_Doctors+"("+DOCTOR_KEY_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+DOCTOR_KEY_USERNAME+" TEXT, "+DOCTOR_KEY_SPECIALITY+" TEXT,"+DOCTOR_KEY_NUMBER+" TEXT,"+DOCTOR_KEY_EMAIL+" TEXT, "+DOCTOR_KEY_PASSWORD+" TEXT, "+DOCTOR_KEY_REPASSWORD+" TEXT,"+DOCTOR_KEY_VERIFIED+"TEXT)";
        String CREATE_USERS_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_Users+"("+USER_KEY_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+USER_KEY_USERNAME+" TEXT, "+USER_KEY_EMAIL+" TEXT, "+USER_KEY_PASSWORD+" TEXT, "+USER_KEY_REPASSWORD+" TEXT, "+USER_KEY_ROLE +" TEXT)";
        db.execSQL(CREATE_SPECIALITIES_TABLE);
        db.execSQL(CREATE_DOCTORS_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
    }

    // upgrading the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SPECIALITY);
        //creating table again
        onCreate(db);
    }

    /**
     * Create, Read, Update, Delete Operations
     */

    //adding a new speciality
    public void addSpeciality(Speciality speciality){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(SPECIALITY_KEY_NAME,speciality.getName());
        // inserting row
        db.insert(TABLE_SPECIALITY,null,values);
        db.close();

    }

    //getting a single contact
    Speciality getSpeciality(int id){
      SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SPECIALITY,new String[]{SPECIALITY_KEY_ID,SPECIALITY_KEY_NAME,},SPECIALITY_KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        Speciality speciality = new Speciality(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
        return speciality;
    }

    // getting all contacts
    public List<Speciality> getAllSpecialities(){
        List<Speciality> specialityList=new ArrayList<Speciality>();
        // select all query
        String selectQuery="SELECT * FROM "+TABLE_SPECIALITY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping to all rows for adding it to list
        if(cursor.moveToFirst()){
            do{
                Speciality speciality = new Speciality();
                speciality.setId(Integer.parseInt(cursor.getString(0)));
                speciality.setName((cursor.getString(1)));
                // add contacts to list
                specialityList.add(speciality);



            }while (cursor.moveToNext());
        }
        return specialityList;
    }
    public List<Speciality> getAllSpecialitiesname(){
        List<Speciality> specialityList=new ArrayList<Speciality>();
        // select all query
        String selectQuery="SELECT name FROM "+TABLE_SPECIALITY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping to all rows for adding it to list
        if(cursor.moveToFirst()){
            do{
                Speciality speciality = new Speciality();
                speciality.setName((cursor.getString(0)));
                // add contacts to list
                specialityList.add(speciality);



            }while (cursor.moveToNext());
        }
        return specialityList;
    }

    // update single contact
   public int updateSpeciality(Speciality  speciality){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SPECIALITY_KEY_NAME,speciality.getName());
        //updating row
       return db.update(TABLE_SPECIALITY,values,SPECIALITY_KEY_ID+"= ?",new String[]{String.valueOf(speciality.getId())});


    }

    //delete the single contact
    public void deleteSpeciality(Speciality speciality){
       SQLiteDatabase db = this.getWritableDatabase();
       db.delete(TABLE_SPECIALITY,SPECIALITY_KEY_ID+"= ?",new String[]{String.valueOf(speciality.getId())});
       db.close();

    }

    // getting contacts count
    public int getSpecialityCount(){
        String countQuery = "SELECT * FROM "+TABLE_SPECIALITY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.rawQuery(countQuery,null);
        cursor.close();
        return  cursor.getCount();

    }

    public void addDoctor(Doctor doctor){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DOCTOR_KEY_USERNAME,doctor.getUsername());
        values.put(DOCTOR_KEY_SPECIALITY,doctor.getSpeciality());
        values.put(DOCTOR_KEY_NUMBER,doctor.getPhone());
        values.put(DOCTOR_KEY_EMAIL,doctor.getEmail());
        values.put(DOCTOR_KEY_PASSWORD,doctor.getPassword());
        values.put(DOCTOR_KEY_REPASSWORD,doctor.getRepassword());

        System.out.println(values.size());

        // inserting row
        db.insert(TABLE_Doctors,null,values);
        db.close();

    }

    public boolean isDoctor (String email) {

        SQLiteDatabase mydb=this.getWritableDatabase();
        Cursor cursor=mydb.rawQuery("Select * from doctors where email =?",new String[] {email});
        if(cursor.getCount()>0)
            return true ;
        else
            return false ;
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

    public boolean checkDoctor (String email,String password) {

        SQLiteDatabase mydb=this.getWritableDatabase();
        Cursor cursor=mydb.rawQuery("Select * from doctors where email =? and password = ? ",new String[] {email,password});
        if(cursor.getCount()>0)
            return true ;
        else
            return false ;
    }public boolean checkUser (String email,String password) {

        SQLiteDatabase mydb=this.getWritableDatabase();
        Cursor cursor=mydb.rawQuery("Select * from users where email =? and password = ? ",new String[] {email,password});
        if(cursor.getCount()>0)
            return true ;
        else
            return false ;
    }

    public boolean checkmail (String email) {

        SQLiteDatabase mydb=this.getWritableDatabase();
        Cursor cursor=mydb.rawQuery("Select * from doctors where email =?",new String[] {email});
        System.out.println(cursor.toString());
        if(cursor.getCount()>0)
            return true ;
        else
            return false ;
    }

    public boolean checkUsermail(String email) {
        SQLiteDatabase mydb=this.getWritableDatabase();
        Cursor cursor=mydb.rawQuery("Select * from users where email =?",new String[] {email});
        System.out.println(cursor.toString());
        if(cursor.getCount()>0)
            return true ;
        else
            return false ;
    }
    // getting all doctors
    public List<Doctor> getAllDoctors(){
        List<Doctor> specialityList=new ArrayList<Doctor>();
        // select all query
        try{
            String selectQuery="SELECT * FROM "+TABLE_Doctors;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery,null);

            // looping to all rows for adding it to list
            if(cursor.moveToFirst()){
                do{
                    Doctor doctor = new Doctor();
                    doctor.setId(Integer.parseInt(cursor.getString(0)));
                    doctor.setUsername((cursor.getString(1)));
                    doctor.setSpeciality((cursor.getString(2)));
                    doctor.setPhone((cursor.getString(3)));
                    doctor.setEmail((cursor.getString(4)));
                    doctor.setPassword((cursor.getString(5)));
                    doctor.setRepassword((cursor.getString(6)));



                    // add contacts to list
                    specialityList.add(doctor);



                }while (cursor.moveToNext());
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return specialityList;
    }
    // getting all doctors
    public List<Doctor> getAllDoctorsNoverivied(){
        List<Doctor> specialityList=new ArrayList<Doctor>();
        // select all query
        try{
            String selectQuery=("SELECT * FROM  doctors where verified = false");
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery,null);

            // looping to all rows for adding it to list
            if(cursor.moveToFirst()){
                do{
                    Doctor doctor = new Doctor();
                    doctor.setId(Integer.parseInt(cursor.getString(0)));
                    doctor.setUsername((cursor.getString(1)));
                    doctor.setSpeciality((cursor.getString(2)));
                    doctor.setPhone((cursor.getString(3)));
                    doctor.setEmail((cursor.getString(4)));
                    doctor.setPassword((cursor.getString(5)));
                    doctor.setRepassword((cursor.getString(6)));



                    // add contacts to list
                    specialityList.add(doctor);



                }while (cursor.moveToNext());
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return specialityList;
    }


}