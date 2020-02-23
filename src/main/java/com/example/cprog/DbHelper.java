package com.example.cprog;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DbHelper extends SQLiteOpenHelper{

    public static final String dbname = "cprog1_chords.db";
    public static final int dbv = 1;

    public DbHelper(@Nullable Context context) {
        super(context, dbname,null,dbv);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS CHORDS( id integer primary key, name text, prog text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CHORDS");
    }

    public boolean addChord(String nm,String chrd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",nm);
        cv.put("prog",chrd);
        boolean m = db.insert("CHORDS", null, cv) > 0;

        return m;

    }

    public ArrayList<String> getAll(){
        SQLiteDatabase dl = this.getReadableDatabase();
        Cursor cr = dl.rawQuery("SELECT * FROM CHORDS",null);
        ArrayList<String> lis = new ArrayList<>();
        if(cr.moveToFirst()){
            do{
                lis.add(cr.getString(0)+"/"+cr.getString(1)+"/"+cr.getString(2));
            }while(cr.moveToNext());
        }
        return lis;
    }

    public String[] get(int pos){
        String[] rs = new String[3];
        SQLiteDatabase b = this.getReadableDatabase();
        Cursor cr = b.rawQuery("SELECT * FROM CHORDS WHERE id = "+pos,null);
        if(cr.moveToFirst()){
            do{
                rs[0] = cr.getString(0);
                rs[1] = cr.getString(1);
                rs[2] = cr.getString(2);
            }while (cr.moveToNext());
        }
        b.close();
        return rs;
    }

    public void del(int pk){

        SQLiteDatabase db = getWritableDatabase();
        //db.rawQuery("DELETE FROM CHORDS WHERE id = "+pk+" ",null);
        db.delete("CHORDS","id =?",new String[]{Integer.toString(pk)});
        db.close();
    }

    public void change(int pk,String nm,String chords){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",nm);
        cv.put("prog",chords);
        db.update("CHORDS",cv,"id=?",new String[]{Integer.toString(pk)});
    }


}
