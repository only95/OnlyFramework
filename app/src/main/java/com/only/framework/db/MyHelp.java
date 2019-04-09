package com.only.framework.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus on 2019/2/13.
 */

public class MyHelp extends SQLiteOpenHelper{

    /**
     *
     * @param context
     * @param name
     * @param factory
     * @param version 版本号，只能时int，同时只能上升
     */
    public MyHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /**
     *这个方法只有在第一次被创建的时候被调用
     * 所以这个方法中执行建表的语句
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table students (_id Integer primary key,name string ,sex string)");
    }

    /**
     *当数据库的版本发生改变时，调用这个方法
     * 在这个方法中可以执行表的更新，先删表在创表
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
