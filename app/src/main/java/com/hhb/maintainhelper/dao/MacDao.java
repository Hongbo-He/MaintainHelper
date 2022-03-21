package com.hhb.maintainhelper.dao;

import com.hhb.maintainhelper.beans.MacBaseBean;
import com.hhb.maintainhelper.beans.MacInfoBean;
import com.hhb.maintainhelper.beans.MacMaintainInfoBean;

import java.util.ArrayList;

public interface MacDao {

    //从txt读取并写入到数据库中
    public void fileToDB(String tableName,String[] keys,String[] values);

    public ArrayList<MacMaintainInfoBean> findDtlInfoByMacCodeAndDate(String keys[]);
    public ArrayList<MacBaseBean> findBaseInfoByMacCode(String keys[]);
    public ArrayList<MacBaseBean> findALLBaseInfo();
    public ArrayList<MacBaseBean> findBaseInfoByDate(String date);
    public ArrayList<MacBaseBean> findBaseInfoByStatus(String status);
    public ArrayList<MacBaseBean> findBaseInfoByMultyCondition(String keys[],String values[]);
    public ArrayList<MacBaseBean> findBaseInfoByMultyCondition(String keys[],String values[],int operation);
    public ArrayList<MacBaseBean> findBaseInfoByMultyCondition_Before_Someday(String keys[],String values[]);

    public void updataStatus(String mac_code,String oldStatus,String newStatus);

    public void restStatus_All();
    public void updataStatus_Finish_Today();
    public void updataStatus_Finish_BeforeToday();


    public MacInfoBean findMacInfoBeanByMacCode(String mac_code);




}
