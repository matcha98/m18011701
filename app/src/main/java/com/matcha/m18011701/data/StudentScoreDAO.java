package com.matcha.m18011701.data;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/17.
 */

public class StudentScoreDAO implements StudentDAO {
    public ArrayList<Student> mylist;
    public StudentScoreDAO()
    {
        mylist = new ArrayList<>();
    }
    //新增
    public boolean add(Student s)
    {
        return mylist.add(s);
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
                return true;
            }
        }
        return false;
    }
}
