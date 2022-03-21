package com.hhb.maintainhelper.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hhb.maintainhelper.AppContext;
import com.hhb.maintainhelper.Utils.LogUtils.LogInfo;
import com.hhb.maintainhelper.Utils.OperationParams;
import com.hhb.maintainhelper.Utils.ToastUtils.ToastInfo;
import com.hhb.maintainhelper.beans.MacBaseBean;
import com.hhb.maintainhelper.beans.MacInfoBean;
import com.hhb.maintainhelper.beans.MacMaintainInfoBean;

import java.util.ArrayList;
import java.util.Calendar;

public class MacDaoImplement implements MacDao {
    private  String tag ="MacDaoImplement";
    DBConnecter connecter =DBConnecter.initDBConnecter(AppContext.initAppContext().getContext());
    SQLiteDatabase db=null;
    @Override
    public void fileToDB(String tableName,String[] keys,String[] values) {
        db = connecter.connectAndGetDb_Write();
        String keyStr =composeStrArr(keys,0);
        String valStr =composeStrArr(values,1);
        String sql="insert or ignore into "+tableName+" ("+keyStr+") values ("+valStr+");";
        LogInfo.showLog(tag,sql,1);
//        insert or ignore into table_name (id,type) values (2,0);
        db.execSQL(sql);
    }

    @Override
    public ArrayList<MacMaintainInfoBean> findDtlInfoByMacCodeAndDate(String[] keys) {
        ArrayList<MacMaintainInfoBean> target =new ArrayList<MacMaintainInfoBean>();
        if(getdb()==true){
            MacMaintainInfoBean maintainInfoBean =null;
            String sql = "SELECT "+DB_PARAMS.MAINTAIN_ITEM+","
                    +DB_PARAMS.MAINTAIN_PERIOD+","
                    +DB_PARAMS.MAINTAIN_TYPE+","
                    +DB_PARAMS.MAINTAIN_CLASS+","
                    +DB_PARAMS.MAINTAIN_STATUS+ " FROM "
                    + DB_PARAMS.TABLE_MAC_MAINTAINS_INFO+" WHERE "
                    +DB_PARAMS.MAC_CODE+"='"+keys[0]+"';";
//                    +"' AND "
//                    +DB_PARAMS.MAINTAIN_TIME +"='"+keys[1]+"';";
            Cursor cursor =db.rawQuery(sql,null);
            while(cursor.moveToNext()){
                maintainInfoBean =new MacMaintainInfoBean();
                maintainInfoBean.setMaintain_item(cursor.getString(0));
                maintainInfoBean.setMaintain_period(cursor.getString(1));
                maintainInfoBean.setMaintain_type(cursor.getString(2));
                maintainInfoBean.setMaintain_class(cursor.getString(3));
                maintainInfoBean.setMaintain_status(cursor.getString(4));
                target.add(maintainInfoBean);
            }
            return target;
        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
            return null;
        }


    }

    @Override
    public ArrayList<MacBaseBean> findBaseInfoByMacCode(String[] keys) {
        ArrayList<MacBaseBean> target =new ArrayList<MacBaseBean>();
        if(getdb()==true){
            MacBaseBean macBaseBean =null;
            String sql = "SELECT "+DB_PARAMS.MAC_CODE+","
                    +DB_PARAMS.MAINTAIN_TIME+","
                    +DB_PARAMS.MAINTAIN_STATUS+ " FROM "
                    + DB_PARAMS.TABLE_MAC_MAINTAINS_INFO+" WHERE "
                    +DB_PARAMS.MAC_CODE+"='"+keys[0]+"';";
            Cursor cursor =db.rawQuery(sql,null);
            while(cursor.moveToNext()){
                macBaseBean =new MacBaseBean();
                macBaseBean.setMac_code(cursor.getString(0));
                macBaseBean.setMaintain_time(cursor.getString(1));
                macBaseBean.setMaintain_status(cursor.getString(2));
                target.add(macBaseBean);
            }
            return target;
        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
            return null;
        }
    }

    @Override
    public ArrayList<MacBaseBean> findALLBaseInfo() {
        ArrayList<MacBaseBean> target =new ArrayList<MacBaseBean>();
        if(getdb()==true){
            MacBaseBean macBaseBean =null;
            String sql = "SELECT "+DB_PARAMS.MAC_CODE+","
                    +DB_PARAMS.MAINTAIN_TIME+","
                    +DB_PARAMS.MAINTAIN_STATUS+ " FROM "
                    + DB_PARAMS.TABLE_MAC_MAINTAINS_INFO+" WHERE "
                    +DB_PARAMS.MAC_CODE+" is not null;";
//                    +"' AND "
//                    +DB_PARAMS.MAINTAIN_TIME +"='"+keys[1]+"';";
            Cursor cursor =db.rawQuery(sql,null);
            while(cursor.moveToNext()){
                macBaseBean =new MacBaseBean();
                macBaseBean.setMac_code(cursor.getString(0));
                macBaseBean.setMaintain_time(cursor.getString(1));
                LogInfo.showLog(tag+tag,cursor.getString(1),1);
                macBaseBean.setMaintain_status(cursor.getString(2));
                target.add(macBaseBean);
            }
            return target;
        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
            return null;
        }
    }

    @Override
    public ArrayList<MacBaseBean> findBaseInfoByDate(String date) {
        ArrayList<MacBaseBean> target =new ArrayList<MacBaseBean>();
        if(getdb()==true){
            MacBaseBean macBaseBean =null;
            String sql = "SELECT "+DB_PARAMS.MAC_CODE+","
                    +DB_PARAMS.MAINTAIN_TIME+","
                    +DB_PARAMS.MAINTAIN_STATUS+ " FROM "
                    + DB_PARAMS.TABLE_MAC_MAINTAINS_INFO+" WHERE "
                    +DB_PARAMS.MAINTAIN_TIME+"='"+date+"';";
            Cursor cursor =db.rawQuery(sql,null);
            while(cursor.moveToNext()){
                macBaseBean =new MacBaseBean();
                macBaseBean.setMac_code(cursor.getString(0));
                macBaseBean.setMaintain_time(cursor.getString(1));
                macBaseBean.setMaintain_status(cursor.getString(2));
                target.add(macBaseBean);
            }
            return target;
        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
            return null;
        }
    }

    @Override
    public ArrayList<MacBaseBean> findBaseInfoByStatus(String status) {
        ArrayList<MacBaseBean> target =new ArrayList<MacBaseBean>();
        if(getdb()==true){
            MacBaseBean macBaseBean =null;
            String sql = "SELECT "+DB_PARAMS.MAC_CODE+","
                    +DB_PARAMS.MAINTAIN_TIME+","
                    +DB_PARAMS.MAINTAIN_STATUS+ " FROM "
                    + DB_PARAMS.TABLE_MAC_MAINTAINS_INFO+" WHERE "
                    +DB_PARAMS.MAINTAIN_STATUS+"='"+status+"';";
            Cursor cursor =db.rawQuery(sql,null);
            while(cursor.moveToNext()){
                macBaseBean =new MacBaseBean();
                macBaseBean.setMac_code(cursor.getString(0));
                macBaseBean.setMaintain_time(cursor.getString(1));
                macBaseBean.setMaintain_status(cursor.getString(2));
                target.add(macBaseBean);
            }
            return target;
        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
            return null;
        }
    }

    @Override
    public ArrayList<MacBaseBean> findBaseInfoByMultyCondition(String[] keys, String[] values) {
        String keyString =composeStrArr(keys,1);
        String valueString =composeStrArr(values,1);
        StringBuilder builder =new StringBuilder();
        if(keys.length!=values.length){
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数组长度不一致，取消查找");
            return null;
        }else {
            for(int i=0;i<keys.length;i++){
                builder.append(keys[i]+"='"+values[i]+"'");
                if (i!=keys.length-1){
                    builder.append(" AND ");
                }
            }

        }

        ArrayList<MacBaseBean> target =new ArrayList<MacBaseBean>();
        if(getdb()==true){
            MacBaseBean macBaseBean =null;
            String sql = "SELECT * FROM "+DB_PARAMS.TABLE_MAC_CODE+" WHERE "+builder.toString()+";";
            LogInfo.showLog(tag,sql,1);
            Cursor cursor =db.rawQuery(sql,null);
            while(cursor.moveToNext()){
                macBaseBean =new MacBaseBean();
                macBaseBean.setMac_code(cursor.getString(1));
                macBaseBean.setMaintain_time(cursor.getString(2));
                macBaseBean.setMaintain_status(cursor.getString(3));
                target.add(macBaseBean);
            }
            return target;
        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
            return null;
        }
    }

    @Override
    public ArrayList<MacBaseBean> findBaseInfoByMultyCondition(String[] keys, String[] values, int operation) {
        ArrayList<MacBaseBean> target =new ArrayList<MacBaseBean>();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        String first =year+"-"+month+"-"+"1";
        String last =year+"-"+month+"-"+"31";

        switch (operation){
            case OperationParams.OP_TODAY:
                target= doFindAct(keys,values,"=",null);
                break;
            case OperationParams.OP_BEFORE_ONE_DAY:
                target= doFindAct(keys,values,"<=",first);
                break;
            case OperationParams.OP_AFTER_ONE_DAY:
                target= doFindAct(keys,values,">=",last);
                 break;
            case OperationParams.OP_WHOLE_MONTH:
                target =doFindAct_Whole_Month(keys,values);
                LogInfo.showLog(tag+"sdaaaaa","--------",1);
                break;
            default:
                break;
        }
        return target;
    }

    private ArrayList<MacBaseBean> doFindAct(String[] keys, String[] values,String signal,String date){
        ArrayList<MacBaseBean> target =new ArrayList<MacBaseBean>();
        String keyString =composeStrArr(keys,1);
        String valueString =composeStrArr(values,1);
        StringBuilder builder =new StringBuilder();
        if(keys.length!=values.length){
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数组长度不一致，取消查找");
            return null;
        }else {
            for(int i=0;i<keys.length;i++){
                if(keys[i].equals(DB_PARAMS.MAINTAIN_TIME)){
                    builder.append(keys[i]+signal+"'"+values[i]+"'");
                }else{
                    builder.append(keys[i]+"='"+values[i]+"'");
                }
                if (i!=keys.length-1){
                    builder.append(" AND ");
                }
            }
        }

        if(getdb()==true){
            MacBaseBean macBaseBean =null;
            String sql="";
            if(date == null){
                sql = "SELECT * FROM "+DB_PARAMS.TABLE_MAC_CODE+" WHERE "+builder.toString()+";";
            }else {
                sql = "SELECT * FROM "+DB_PARAMS.TABLE_MAC_CODE+" WHERE "+builder.toString()+" AND '"+date+"'"+signal+keys[0]+";";
            }

            LogInfo.showLog(tag,sql,1);
            Cursor cursor =db.rawQuery(sql,null);
            while(cursor.moveToNext()){
                macBaseBean =new MacBaseBean();
                macBaseBean.setMac_code(cursor.getString(1));
                macBaseBean.setMaintain_time(cursor.getString(2));
                macBaseBean.setMaintain_status(cursor.getString(3));
                target.add(macBaseBean);
            }
            return target;
        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
            return null;
        }
    }
    private ArrayList<MacBaseBean> doFindAct_Whole_Month(String[] keys, String[] values){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        String first =year+"-"+month+"-"+"1";
        String last =year+"-"+month+"-"+"31";

        ArrayList<MacBaseBean> target =new ArrayList<MacBaseBean>();
        String keyString =composeStrArr(keys,1);
        String valueString =composeStrArr(values,1);
        StringBuilder builder =new StringBuilder();
        if(keys.length!=values.length){
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数组长度不一致，取消查找");
            return null;
        }else {
            for(int i=0;i<keys.length-1;i++){
                    builder.append(keys[i]+"='"+values[i]+"'");
                if (i!=keys.length-2){
                    builder.append(" AND ");
                }
            }
        }

        if(getdb()==true){
            MacBaseBean macBaseBean =null;
            String sql = "SELECT * FROM "+DB_PARAMS.TABLE_MAC_CODE+" WHERE "+keys[1]+"='"+values[1]+"' AND "+keys[0]+"<='"+last+"' AND "+keys[0]+">='"+first+"';";
            LogInfo.showLog(tag+"-----------"+tag,sql,1);
            Cursor cursor =db.rawQuery(sql,null);
            while(cursor.moveToNext()){
                macBaseBean =new MacBaseBean();
                macBaseBean.setMac_code(cursor.getString(1));
                macBaseBean.setMaintain_time(cursor.getString(2));
                macBaseBean.setMaintain_status(cursor.getString(3));
                target.add(macBaseBean);
            }
            return target;
        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
            return null;
        }
    }

    @Override
    public ArrayList<MacBaseBean> findBaseInfoByMultyCondition_Before_Someday(String[] keys, String[] values) {
        String keyString =composeStrArr(keys,1);
        String valueString =composeStrArr(values,1);
        StringBuilder builder =new StringBuilder();
        if(keys.length!=values.length){
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数组长度不一致，取消查找");
            return null;
        }else {
            for(int i=0;i<keys.length;i++){
                if(keys[i].equals(DB_PARAMS.MAINTAIN_TIME)){
                    builder.append(keys[i]+"<='"+values[i]+"'");
                }else{
                    builder.append(keys[i]+"='"+values[i]+"'");
                }
                if (i!=keys.length-1){
                    builder.append(" AND ");
                }
            }

        }
        ArrayList<MacBaseBean> target =new ArrayList<MacBaseBean>();
        if(getdb()==true){
            MacBaseBean macBaseBean =null;
            String sql = "SELECT * FROM "+DB_PARAMS.TABLE_MAC_CODE+" WHERE "+builder.toString()+";";
            LogInfo.showLog(tag,sql,1);
            Cursor cursor =db.rawQuery(sql,null);
            while(cursor.moveToNext()){
                macBaseBean =new MacBaseBean();
                macBaseBean.setMac_code(cursor.getString(1));
                macBaseBean.setMaintain_time(cursor.getString(2));
                macBaseBean.setMaintain_status(cursor.getString(3));
                target.add(macBaseBean);
            }
            return target;
        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
            return null;
        }
    }

    @Override
    public void updataStatus(String mac_code, String oldStatus, String newStatus) {
        if(getdb()==true){
            if(oldStatus.equals("已完成")){
                ToastInfo.showToast(AppContext.initAppContext().getContext(),"当前状态为"+oldStatus+",因此不对其状态进行更改");
            }else {
                String sql_table_Mac_code =" UPDATE "+DB_PARAMS.TABLE_MAC_CODE+" SET "+DB_PARAMS.MAINTAIN_STATUS+"='"+newStatus
                        +"' WHERE "+DB_PARAMS.MAC_CODE+"='"+mac_code+"'";
                String sql_table_Maintain_info =" UPDATE "+DB_PARAMS.TABLE_MAC_MAINTAINS_INFO+" SET "+DB_PARAMS.MAINTAIN_STATUS+"='"+newStatus
                        +"' WHERE "+DB_PARAMS.MAC_CODE+"='"+mac_code+"'";

                db.execSQL(sql_table_Mac_code);
                db.execSQL(sql_table_Maintain_info);
            }

        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(), "数据库打开失败");
            return;
        }

    }

    @Override
    public void restStatus_All() {
        if(getdb()==true){
        String sql_table_Mac_code =" UPDATE "+DB_PARAMS.TABLE_MAC_CODE+" SET "+DB_PARAMS.MAINTAIN_STATUS+"='待保养';";
        String sql_table_Maintain_info =" UPDATE "+DB_PARAMS.TABLE_MAC_MAINTAINS_INFO+" SET "+DB_PARAMS.MAINTAIN_STATUS+"='待保养';";

        db.execSQL(sql_table_Mac_code);
        db.execSQL(sql_table_Maintain_info);
        }else {
        ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
        return ;
        }
    }

    @Override
    public void updataStatus_Finish_Today() {

        String today =Calendar.getInstance().get(Calendar.YEAR)+"-"
                    +(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"
                    +Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        if(getdb()==true){
            String sql_table_Mac_code =" UPDATE "+DB_PARAMS.TABLE_MAC_CODE+" SET "+DB_PARAMS.MAINTAIN_STATUS+"='已完成' WHERE "+DB_PARAMS.MAINTAIN_TIME+"='"+today+"';";
            String sql_table_Maintain_info =" UPDATE "+DB_PARAMS.TABLE_MAC_MAINTAINS_INFO+" SET "+DB_PARAMS.MAINTAIN_STATUS+"='已完成' WHERE "+DB_PARAMS.MAINTAIN_TIME+"='"+today+"';";

            db.execSQL(sql_table_Mac_code);
            db.execSQL(sql_table_Maintain_info);
        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
            return ;
        }
    }

    @Override
    public void updataStatus_Finish_BeforeToday() {
        String today =Calendar.getInstance().get(Calendar.YEAR)+"-"
                +(Calendar.getInstance().get(Calendar.MONTH)+1)+"-"
                +Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        if(getdb()==true){
            String sql_table_Mac_code =" UPDATE "+DB_PARAMS.TABLE_MAC_CODE+" SET "+DB_PARAMS.MAINTAIN_STATUS+"='已完成' WHERE "+DB_PARAMS.MAINTAIN_TIME+"<'"+today+"';";
            String sql_table_Maintain_info =" UPDATE "+DB_PARAMS.TABLE_MAC_MAINTAINS_INFO+" SET "+DB_PARAMS.MAINTAIN_STATUS+"='已完成' WHERE "+DB_PARAMS.MAINTAIN_TIME+"<'"+today+"';";

            db.execSQL(sql_table_Mac_code);
            db.execSQL(sql_table_Maintain_info);
        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
            return ;
        }
    }

    @Override
    public MacInfoBean findMacInfoBeanByMacCode(String mac_code) {
        if(getdb()==true){
            MacInfoBean macInfoBean =null;
            String sql = "SELECT "+DB_PARAMS.MAC_CODE+","
                    +DB_PARAMS.TYPE_CODE+","
                    +DB_PARAMS.TYPE_NAME+","
                    +DB_PARAMS.MAC_NAME+ " FROM "
                    + DB_PARAMS.TABLE_MAC_INFO+" WHERE "
                    +DB_PARAMS.MAC_CODE+"='"+mac_code+"';";
//                    +"' AND "
//                    +DB_PARAMS.MAINTAIN_TIME +"='"+keys[1]+"';";
            Cursor cursor =db.rawQuery(sql,null);
            while(cursor.moveToNext()){
                macInfoBean =new MacInfoBean();
//                LogInfo.showLog(tag,cursor.getString(i=(i+1)),1);

                macInfoBean.setMac_code(cursor.getString(0));
                macInfoBean.setType_code(cursor.getString(1));
                macInfoBean.setType_name(cursor.getString(2));
                macInfoBean.setMac_name(cursor.getString(3));
            }
            return macInfoBean;
        }else {
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败");
            return null;
        }
    }

    public void fileToDB_spec(String tableName,String[] keys,String[] values,Object obj) {
        db = connecter.connectAndGetDb_Write();
        String keyStr =composeStrArr(keys,0);
        String valStr =composeStrArr(values,2);
        String sql="insert or ignore into "+tableName+" ("+keyStr+") values ("+valStr+");";
        LogInfo.showLog(tag,sql,1);
//        insert or ignore into table_name (id,type) values (2,0);
        db.execSQL(sql);
    }

    private boolean getdb(){
        try {
            this.db = connecter.connectAndGetDb_Write();
            return true;
        }catch (Exception e){
            ToastInfo.showToast(AppContext.initAppContext().getContext(),"数据库打开失败"+e.toString());
            return false;
        }

    }

    private String composeStrArr(String[] arr,int code){
        StringBuilder sb =new StringBuilder();
        switch (code){
            //有数字的方式
            case 0:
                if(arr.length==1){
                    sb.append(arr[0]); }
                else {
                    for(int i = 0;i<arr.length;i++){
                        if(i==arr.length-1){
                            sb.append(arr[i]);
                        }else {
                            sb.append(arr[i]+",");
                        }
                    }
                }
                break;
            case 1:
                if(arr.length==1){
                    sb.append("'"+arr[0]+"'"); }
                else {
                    for(int i = 0;i<arr.length;i++){
                        if(i==arr.length-1){
                            sb.append("'"+arr[i]+"'");
                        }else {
                            sb.append("'"+arr[i]+"'"+",");
                        }
                    }
                }
                break;
            case 2:
                if(arr.length==1){
                    sb.append("'"+arr[0]+"'"); }
                else {
                    for(int i = 0;i<arr.length;i++){
                        if(i==arr.length-1){
                            sb.append("'"+arr[i]+"'");
                        }else {
                            sb.append("'"+arr[i]+"'"+",");
                        }
                    }
                }
                LogInfo.showLog(tag,sb.toString(),1);
                break;
            default:
                break;

        }
      return  sb.toString();
    };
}
