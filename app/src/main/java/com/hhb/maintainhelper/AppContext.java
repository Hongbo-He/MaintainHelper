package com.hhb.maintainhelper;

import android.content.Context;

public class AppContext {

    private static AppContext appContext;
    private static Context context;
    private AppContext(){ };

    public static AppContext initAppContext(){
        if (appContext==null){
            appContext=new AppContext();
        }
        return  appContext;
    }

    public Context getContext() {
        return context;
    }

    public  void setContext(Context context) {
        this.context = context;
    }

}
