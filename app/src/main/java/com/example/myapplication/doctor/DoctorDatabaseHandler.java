package com.example.myapplication.doctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DoctorDatabaseHandler extends SQLiteOpenHelper {
    // static variables
    // database version
    private static final int DATABASE_VERSION = 1;
    // database name
    private static final String DATABASE_NAME = "test.db";

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



    public DoctorDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // creating the contacts table
    @Override
    public void onCreate(SQLiteDatabase db) {
        // query for create table

        String CREATE_DOCTORS_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_Doctors+"("+DOCTOR_KEY_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+DOCTOR_KEY_USERNAME+" TEXT, "+DOCTOR_KEY_SPECIALITY+" TEXT,"+DOCTOR_KEY_NUMBER+" TEXT,"+DOCTOR_KEY_EMAIL+" TEXT, "+DOCTOR_KEY_PASSWORD+" TEXT, "+DOCTOR_KEY_REPASSWORD+" TEXT)";
        db.execSQL(CREATE_DOCTORS_TABLE);
    }

    // upgrading the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Doctors);
        //creating table again
        onCreate(db);
    }

    /**
     * Create, Read, Update, Delete Operations
     */

    //adding a new speciality
//    public void addDoctor(Doctor doctor){
//        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(DOCTOR_KEY_USERNAME,doctor.getUsername());
//        values.put(DOCTOR_KEY_SPECIALITY,doctor.getSpeciality());
//        values.put(DOCTOR_KEY_NUMBER,doctor.getPhone());
//        values.put(DOCTOR_KEY_EMAIL,doctor.getEmail());
//        values.put(DOCTOR_KEY_PASSWORD,doctor.getPassword());
//        values.put(DOCTOR_KEY_REPASSWORD,doctor.getRepassword());
//        System.out.println(values.size());
//
//        // inserting row
//        db.insert(TABLE_Doctors,null,values);
//        db.close();
//
//    }

    //getting a single contact
    Doctor getdoctor(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_Doctors,new String[]{DOCTOR_KEY_ID,DOCTOR_KEY_USERNAME,DOCTOR_KEY_SPECIALITY,DOCTOR_KEY_NUMBER},
                DOCTOR_KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        Doctor doctor = new Doctor();
        return doctor;
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

    // update single contact
    public int updateSpeciality(Doctor  doctor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DOCTOR_KEY_USERNAME,doctor.getUsername());
        values.put(DOCTOR_KEY_SPECIALITY,doctor.getSpeciality());
        values.put(DOCTOR_KEY_NUMBER,doctor.getPhone());
        //updating row
        return db.update(TABLE_Doctors,values,DOCTOR_KEY_ID+"= ?",new String[]{String.valueOf(doctor.getId())});


    }

    public int setVerified(int doctorId,boolean value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DOCTOR_KEY_VERIFIED,value);
        //updating row
        return db.update(TABLE_Doctors,values,DOCTOR_KEY_ID+"= ?",new String[]{String.valueOf(doctorId)});


    }

    //delete the single contact
    public void deleteDoctor(Doctor doctor){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Doctors,DOCTOR_KEY_ID+"= ?",new String[]{String.valueOf(doctor.getId())});
        db.close();

    }

    // getting contacts count
    public int getDoctorCount(){
        String countQuery = "SELECT * FROM "+TABLE_Doctors;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.rawQuery(countQuery,null);
        cursor.close();
        return  cursor.getCount();

    }

}