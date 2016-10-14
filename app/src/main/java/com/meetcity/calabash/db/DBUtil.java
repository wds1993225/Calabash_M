package com.meetcity.calabash.db;

import android.content.Context;
import android.database.SQLException;

import com.meetcity.calabash.DivinationActivity;

import java.io.IOException;

/**
 * Created by wds1993225 on 2016/9/13.
 */
public class DBUtil {

    //TODO 子线程中执行
    //将asset文件夹下的db拷贝到data\下
    public static void initDb(Context context){
        DBHelper dbHelper = new DBHelper(context);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("Unable to create database");
        }
        try {
            dbHelper.openDataBase();
        }catch (SQLException e){
            throw e;
        }
    }
}
