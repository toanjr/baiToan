package com.example.baitest1.databaes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;

import com.example.baitest1.modor.User;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class UserDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "users_db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "users";
    public static final String IDD_COL = "id";
    public static final String NAME_COL = "name";
    public static final String EMAIL_COL = "email";
    public static final String PASSWORD_COL = "password";
    public static final String COMPERPASSWORD_COL = "comperpassword";
    public static final String CREATED_AT_COL = "created_at";
    public static final String UPDATED_AT_COL = "updated_at";



    public UserDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + "("
                + IDD_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " VARCHAR(60) NOT NULL, "
                + PASSWORD_COL + " VARCHAR(200) NOT NULL, "
                + EMAIL_COL  + " VARCHAR(60)  NOT NULL, "
                + COMPERPASSWORD_COL + " VARCHAR(100)  NOT NULL, "
                + CREATED_AT_COL + " DATETIME , "
                + UPDATED_AT_COL + " DATETIME )";
        sqLiteDatabase.execSQL(query);// tao bang//
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);// xoa bang neu co loi
        onCreate(sqLiteDatabase); //tao lai bang
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public long addNewUser(
            String name,
            String password,
            String email,
            String comperpassword){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        String dateNow = sdf.format(new Date());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, name);
        values.put(PASSWORD_COL,password);
        values.put(EMAIL_COL, email);
        values.put(COMPERPASSWORD_COL, comperpassword);
        values.put(CREATED_AT_COL, dateNow);
        long add = sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();
        return add;
        ///////////////////////////////

    }
    @SuppressLint("Range")
    public User getInforuser(String name, String password){
        User user = new User();

        try{
            SQLiteDatabase db = this.getReadableDatabase();//doc du lieu
            // tao mang chua cac cot du lieu can lay thong tin///
            String[] colums = {IDD_COL, NAME_COL, EMAIL_COL , PASSWORD_COL , COMPERPASSWORD_COL};// ten mang
            String condition = NAME_COL  +  " =? " + " AND " + PASSWORD_COL + " =? ";// dieu kien
            String[] params = { name , password }; // du lieu
            Cursor cursor = db.query(TABLE_NAME, colums, condition, params, null,null,null);
            if (cursor.getCount() > 0){ // check xem co du lieu tra ve khong///
                cursor.moveToFirst();// lay ra mot roe data dau tien//
                // do du lieu vao model//
                user.setId(cursor.getInt(cursor.getColumnIndex(IDD_COL)));
                user.setName(cursor.getString(cursor.getColumnIndex(NAME_COL)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL_COL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD_COL)));
                user.setComperpassword(cursor.getString(cursor.getColumnIndex(COMPERPASSWORD_COL)));
            }
            cursor.close();
            db.close();
            /////////////
        }catch (Exception e){
            throw new RuntimeException();
        }
        return user;
    }
    public int updatePassword(String name, String email , String newPassword){
        //thuc hanh cau lenh updateSQL lite
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD_COL, newPassword);
        String condition = NAME_COL + " =? " + " AND " + EMAIL_COL + " =? ";
        String[] params  = { name , email};
        int update = db.update(TABLE_NAME,values , condition , params );
        db.close();
        return update;
    }










}
