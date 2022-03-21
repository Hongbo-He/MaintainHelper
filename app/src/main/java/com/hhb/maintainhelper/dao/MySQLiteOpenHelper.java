package com.hhb.maintainhelper.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.hhb.maintainhelper.Utils.LogUtils.LogInfo;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private String tag ="MySQLiteOpenHelper.class";
    private Context context;


    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context =context;
    }

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//
        String MAC_CODE = "CREATE TABLE IF NOT EXISTS "+DB_PARAMS.TABLE_MAC_CODE+"("
                                         +DB_PARAMS.ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                                         +DB_PARAMS.MAC_CODE+" TEXT,"//UNIQUE ON CONFLICT IGNORE);";
                                         +DB_PARAMS.MAINTAIN_TIME+" TEXT,"// UNIQUE ON CONFLICT IGNORE);";
                                         +DB_PARAMS.MAINTAIN_STATUS+" TEXT,"// UNIQUE ON CONFLICT IGNORE);";
                                         +DB_PARAMS.DATA_ID+" TETX UNIQUE ON CONFLICT IGNORE"+");";

        String MAC_INFO = "CREATE TABLE IF NOT EXISTS "+DB_PARAMS.TABLE_MAC_INFO+"("
                                            +DB_PARAMS.ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                                            +DB_PARAMS.MAC_CODE+" TEXT,"
                                            +DB_PARAMS.TYPE_CODE+" TEXT,"
                                            +DB_PARAMS.TYPE_NAME+" TEXT,"
                                            +DB_PARAMS.MAC_NAME+" TEXT,"//);";
                                            +DB_PARAMS.DATA_ID+" TETX UNIQUE ON CONFLICT IGNORE"+");";

        String MAC_MAINTAINS_INFO = "CREATE TABLE IF NOT EXISTS "+DB_PARAMS.TABLE_MAC_MAINTAINS_INFO+"("
                                            +DB_PARAMS.ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                                            +DB_PARAMS.MAC_CODE+" TEXT,"
                                            +DB_PARAMS.MAINTAIN_ITEM+" TEXT,"
                                            +DB_PARAMS.MAINTAIN_PERIOD+" TEXT,"
                                            +DB_PARAMS.MAINTAIN_CLASS+" TEXT,"
                                            +DB_PARAMS.MAINTAIN_CONSUME_TIME+" TEXT,"
                                            +DB_PARAMS.MAINTAIN_TIME+" TEXT,"
                                            +DB_PARAMS.MAINTAIN_TYPE+" TEXT,"
                                            +DB_PARAMS.MAINTAIN_STATUS+" TEXT,"
                                            +DB_PARAMS.DATA_ID+" TETX UNIQUE ON CONFLICT IGNORE"+");";
//                ",UNIQUE(\""+DB_PARAMS.MAINTAIN_JUDGE+"\"));";
        LogInfo.showLog(tag,MAC_MAINTAINS_INFO,1);
//    //编号 型号编码 型号名称 设备名称
//    CREATE TABLE MAC_CODE(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,mac_code TEXT,type_code TEXT,type_name TEXT,mac_name TEXT)
//
//    //编号
//    CREATE TABLE MAC_CODE(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,mac_code TEXT,maintain_item TEXT,maintain_period TEXT,maintain_class TEXT,maintain_consume_time TEXT,maintain_time TEXT,maintain_type TEXT,maintain_status TEXT)
        db.execSQL(MAC_CODE);
        db.execSQL(MAC_INFO);
        db.execSQL(MAC_MAINTAINS_INFO);
//        MP220210903	C0694	MOD-B024	DH-IV	高速倒邊機	晶片生產部車間		P1-1F晶片車間	LIT-EQU502-105-05.03	皮帶檢查;用時:0.5H;週期:月保养	月	二級保養	30	2022年3月17日	计划性	鄭王建	2022年3月17日 tt 08:15:26	2022年3月17日 tt 16:45:12	已完成	510		2022年2月8日 tt 00:02:19	鄭王建[C02426]	2022年3月17日 tt 16:45:42
//        MP220210902	C0694	MOD-B024	DH-IV	高速倒邊機	晶片生產部車間		P1-1F晶片車間	LIT-EQU502-105-05.01	軸承加潤滑油;用時:0.5H;週期:月保养	月	二級保養	30	2022年3月17日	计划性	鄭王建	2022年3月17日 tt 08:15:26	2022年3月17日 tt 16:45:11	已完成	510		2022年2月8日 tt 00:02:19	鄭王建[C02426]	2022年3月17日 tt 16:45:41
//        MP220210780	C0253	MOD-B020	AK-30	高速倒邊機	晶片生產部車間		P1-1F晶片車間	LIT-EQU502-105-01.03	皮帶檢查;用時:0.5H;週期:月保养	月	二級保養	30	2022年3月17日	计划性	鄭王建	2022年3月17日 tt 08:15:46	2022年3月17日 tt 16:44:38	已完成	509		2022年2月8日 tt 00:02:14	鄭王建[C02426]	2022年3月17日 tt 16:45:08
//        MP220210781	C0253	MOD-B020	AK-30	高速倒邊機	晶片生產部車間		P1-1F晶片車間	LIT-EQU502-105-01.04	軸承螺絲檢查;用時:0.5H;週期:月保养	月	二級保養	30	2022年3月17日	计划性	鄭王建	2022年3月17日 tt 08:15:46	2022年3月17日 tt 16:44:38	已完成	509		2022年2月8日 tt 00:02:14	鄭王建[C02426]	2022年3月17日 tt 16:45:08
//        0              1          2           3       4           5           6           7                   8                        9                  10       11  12         13       14      15                  16                          17             18       19  20                21           22                         23
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
