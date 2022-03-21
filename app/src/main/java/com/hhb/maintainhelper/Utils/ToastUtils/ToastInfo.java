package com.hhb.maintainhelper.Utils.ToastUtils;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class ToastInfo {
    public static void showToast(Context context,String info){
        Toast.makeText(context,info,Toast.LENGTH_SHORT).show();
    }
    public static void showToastLong(Context context,String info){
        Toast.makeText(context,info,Toast.LENGTH_LONG).show();
    }
    public static void showToast(Context context,String info,int length){
        Toast.makeText(context,info,length).show();
    }


}
