package com.hhb.maintainhelper.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hhb.maintainhelper.Utils.LogUtils.LogInfo;

public class DBConnecter {
    private static DBConnecter dbConnecter ;

    private static String tag ="DBConnecter";
    private static Context context;
    private static SQLiteDatabase db;
    private static MySQLiteOpenHelper mySQLiteOpenHelper;
    private DBConnecter(Context context){
        this.context =context;
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context,DB_PARAMS.DB_NAME,null,1);
    }

    public static DBConnecter initDBConnecter(Context context){
        if (dbConnecter==null){
            dbConnecter=new DBConnecter(context);
        }
        return  dbConnecter;
    }

    public SQLiteDatabase connectAndGetDb_Write(){
        db =mySQLiteOpenHelper.getWritableDatabase();
        return  db;
    }

    public SQLiteDatabase connectAndGetDb_Read(){
//        mySQLiteOpenHelper = new MySQLiteOpenHelper(context,DB_PARAMS.DB_NAME,null,1);
        db =mySQLiteOpenHelper.getReadableDatabase();
        return  db;
    }

    public boolean closeDB(){
        boolean flag =false;
        if (db==null){
            //数据库没打开
            LogInfo.showLog(tag,"数据库null",1);
            return  false;
        }else {
            if(!db.isOpen()){
                //数据库已经关了
                LogInfo.showLog(tag,"数据库没打开",0);
                return true;
            }else {
                db.close();
                if (!db.isOpen()){
                    //数据库已经关了
                    LogInfo.showLog(tag,"数据库已经关闭",0);
                    return true;
                }else {
                    LogInfo.showLog(tag,"数据库关闭失败",1);
                    return false;
                }
            }
        }

    }

}
