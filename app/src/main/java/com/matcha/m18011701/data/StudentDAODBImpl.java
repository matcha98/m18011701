package com.matcha.m18011701.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/22.
 */

public class StudentDAODBImpl implements StudentDAO {

    Context context;
    SQLiteDatabase database;
    public StudentDAODBImpl(Context context)
    {
        this.context=context;
        MyHelper helper=new MyHelper(context);
        database=helper.getWritableDatabase();
    }

    @Override
    public boolean add(Student s) {
        ContentValues values=new ContentValues();
        values.put("_id",s.id);
        values.put("name",s.name);
        values.put("score",s.score);
        database.insert("students",null,values);
        return true;
    }

    @Override
    public ArrayList<Student> getList() {
        ArrayList<Student> mylist=new ArrayList<>();
        Cursor c=database.query("students",new String[]{"_id","name","score"},null,null,null,null,null);
        if(c.moveToFirst())
        {
            do
            {
                Student s=new Student(c.getInt(0),c.getString(1),c.getInt(2));
                mylist.add(s);
            }while(c.moveToNext());
        }
        return mylist;
    }

    @Override
    public Student getStudent(int id) {
        Cursor c=database.query("students",new String[]{"_id","name","score"},"_id=?",new String[]{String.valueOf(id)},null,null,null);
        if(c.moveToFirst())
        {
            Student s=new Student(c.getInt(0),c.getString(1),c.getInt(2));
            return s;
        }
        return null;
    }

    @Override
    public boolean update(Student s) {
        ContentValues values=new ContentValues();
        values.put("name",s.name);
        values.put("score",s.score);
        database.update("students",values,"_id=?",new String[]{String.valueOf(s.id)});
        return true;
    }

    @Override
    public boolean delete(int id) {
        database.delete("students","_id=?",new String[]{String.valueOf(id)});
        return true;
    }
}
