package com.matcha.m18011701.data;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Student on 2018/1/18.
 */

public class StudentFileDAO {
    public ArrayList<Student> mylist;
    private Context context;
    public StudentFileDAO(Context context)
    {
        this.context=context;
        mylist = new ArrayList<>();
    }
    //寫入檔案
    public void saveFile()
    {
        File f=new File(context.getFilesDir(),"mydata.txt");
        FileWriter fw;
        try {
            fw=new FileWriter(f);
            Gson gson=new Gson();
            String data=gson.toJson(mylist);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //讀取檔案
    public void load()
    {
        File f=new File(context.getFilesDir(),"mydata.txt");
        FileReader fr;
        try {
            fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String str=br.readLine();
            Gson gson=new Gson();
            mylist=gson.fromJson(str,new TypeToken<ArrayList<Student>>(){}.getType());
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //新增
    public boolean add(Student s)
    {
        mylist.add(s);
        saveFile();
        return true;
    }
    //查詢所有
    public ArrayList<Student> getList()
    {
        load();
        return mylist;
    }
    //查詢
    public Student getStudent(int id)
    {
        load();
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
        load();
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
        load();
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
