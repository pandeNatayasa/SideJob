package com.tr.nata.sidejob.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tr.nata.sidejob.Model.DataUserItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "pemrograman_mobile.db";

    //Tabel Kategori
    public static final String TABLE_NAME_KATEGORI = "category_table";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_KATEGORI = "kategori";

    //Tabel Data Jasa
    public static final String TABLE_NAME_JASA = "data_jasa_table";
    //    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ID_KATEGORI = "id_kategori";
    public static final String COLUMN_ID_USER = "id_user";
    public static final String COLUMN_PEKERJAAN = "pekerjaan";
    public static final String COLUMN_USIA="usia";
    public static final String COLUMN_NO_TELP="no_telp";
    public static final String COLUMN_EMAIL_JASA="email";
    public static final String COLUMN_STATUS="status";
    public static final String COLUMN_ALAMAT_JASA="alamat";
    public static final String COLUMN_ID_KECAMATAN="id_kecamatan";

    //Tabel Data User
    public static final String TABLE_NAME_USER = "data_user_table";
    //    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME_USER = "name";
    public static final String COLUMN_EMAIL_USER="email";
    public static final String COLUMN_PASSWORD_USER="password";
    public static final String COLUMN_JK_USER = "jenis_kelamin";
    public static final String COLUMN_NO_TELP_USER="no_telp";
    public static final String COLUMN_TANGGAL_LAHIR_USER = "tanggal_lahir";


    //pembuatan database
    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    //pembuatan tabel
    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table " + TABLE_NAME_KATEGORI + "(id integer primary key autoincrement, kategori text);");
//
//        db.execSQL("create table " + TABLE_NAME_JASA + "(" +
//                COLUMN_ID +" integer primary key autoincrement," +
//                COLUMN_ID_KATEGORI+" integer," +
//                COLUMN_ID_USER+" integer," +
//                COLUMN_PEKERJAAN+" text," +
//                COLUMN_USIA+" integer," +
//                COLUMN_NO_TELP+" text," +
//                COLUMN_EMAIL_JASA+" text," +
//                COLUMN_STATUS+" text," +
//                COLUMN_ALAMAT_JASA+" text," +
//                COLUMN_ID_KECAMATAN+" integer);");

        db.execSQL("create table "+TABLE_NAME_USER+"(" +
                COLUMN_ID+" integer primary key autoincrement," +
                COLUMN_NAME_USER+" text," +
                COLUMN_EMAIL_USER+" text," +
                COLUMN_PASSWORD_USER+" text," +
                COLUMN_JK_USER+" text," +
                COLUMN_NO_TELP_USER+" text," +
                COLUMN_TANGGAL_LAHIR_USER+" text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_KATEGORI);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_JASA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        onCreate(db);
    }

    //Untuk Tabel Data User
    public boolean insertDataUser(String name,String email,String password,String jenis_kelamin, String no_telp,String tanggal_lahir){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_USER,name);
        contentValues.put(COLUMN_EMAIL_USER,email);
        contentValues.put(COLUMN_PASSWORD_USER,password);
        contentValues.put(COLUMN_JK_USER,jenis_kelamin);
        contentValues.put(COLUMN_NO_TELP_USER,no_telp);
        contentValues.put(COLUMN_TANGGAL_LAHIR_USER,tanggal_lahir);
        long result = db.insert(TABLE_NAME_USER,null,contentValues);

        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getDataUser(int id_user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME_USER+"where"+COLUMN_ID+"="+id_user,null);
        return res;
    }

    public void deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataUserItem.Entry.TABLE_NAME_USER,COLUMN_ID+"="+id,null);
    }

    public List<DataUserItem> selectDataUser(int id_user){
        List<DataUserItem> dataUserItems = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query(DataUserItem.Entry.TABLE_NAME_USER,null,COLUMN_ID+"="+id_user,null ,null, null,null);
        int count = cursor.getCount();
        if (count>0){
            while (cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_NAME_USER));
                String email = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_EMAIL_USER));
                String jk = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_JK_USER));
                String no_telp = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_NO_TELP_USER));
                String tanggal_lahir = cursor.getString(cursor.getColumnIndex(DataUserItem.Entry.COLUMN_TANGGAL_LAHIR_USER));

                DataUserItem temp = new DataUserItem();
                temp.setId(id);
                temp.setName(name);
                temp.setEmail(email);
                temp.setJenisKelamin(jk);
                temp.setNoTelp(no_telp);
                temp.setTanggalLahir(tanggal_lahir);
                dataUserItems.add(temp);
            }

        }
        cursor.close();
        sqLiteDatabase.close();
        return dataUserItems;
    }

}
