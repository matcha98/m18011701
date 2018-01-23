package com.matcha.m18011701.data;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.matcha.m18011701.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Student on 2018/1/18.
 */

public class StudentClouldDAO implements StudentDAO {
    public ArrayList<Student> mylist;
    private Context context;
    private FirebaseDatabase database;
    DatabaseReference myRef;

    public StudentClouldDAO(final Context context)
    {
        this.context=context;
        mylist = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("score");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Gson gson = new Gson();
                mylist = gson.fromJson(value, new TypeToken<ArrayList<Student>>(){}.getType());
                ((MainActivity)context).refreshData();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        if(mylist==null)
        {
            mylist=new ArrayList<>();
        }
    }

    //寫入檔案
    public void saveFile()
    {
        Gson gson=new Gson();
        String data=gson.toJson(mylist);

        myRef.setValue(data);

    }

    //新增
    public boolean add(Student s)
    {
        if (mylist == null)
        {
            mylist = new ArrayList<>();
        }
        mylist.add(s);
        saveFile();
        return true;
    }
    //查詢所有
    public ArrayList<Student> getList()
    {
        return mylist;
    }
    //查詢
    public Student getStudent(int id)
    {
        for(Student s: mylist)
        {
            if(s.id==id)
            {
                return s;
            }
        }
        return null;
    }
    //更新
    public boolean update(Student s)
    {
        for(int i=0;i<mylist.size();i++)
        {
            if(mylist.get(i).id==s.id)
            {
                mylist.get(i).name=s.name;
                mylist.get(i).score=s.score;
                saveFile();
                return true;
            }
        }

        return false;
    }
    //刪除
    public boolean delete(int id)
    {
        for(int i=0;i<mylist.size();i++)
        {
            if(mylist.get(i).id==id)
            {
                mylist.remove(i);
                saveFile();
                return true;
            }
        }
        return false;
    }
}
