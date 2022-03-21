package com.hhb.maintainhelper.Utils.LogUtils;

import android.util.Log;

public class LogInfo {
    public static void showLog(String origin,String info,int level){
        switch (level){
            case 0:
                Log.v("来自 "+origin,"消息是 "+info);
                break;
            case 1:
                Log.e("来自 "+origin,"消息是 "+info);
                break;
            default:
                Log.i("来自 "+origin,"消息是 "+info);
                break;

        }
    }
}
