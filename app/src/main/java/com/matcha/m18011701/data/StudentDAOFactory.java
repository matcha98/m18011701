package com.matcha.m18011701.data;

import android.content.Context;

/**
 * Created by Student on 2018/1/18.
 */

public class StudentDAOFactory {

    public static StudentDAO getInstance(Context context,DBType dbType)
    {
        switch (dbType)
        {
            case MEMORY:
                return new StudentScoreDAO();
            case FILE:
                return new StudentFileDAO(context);
            case DB:
                return new StudentDAODBImpl(context);
            case CLOULD:
                return new StudentClouldDAO(context);
        }
        return null;
    }
}
