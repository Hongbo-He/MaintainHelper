package com.hhb.maintainhelper.ui;

import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.hhb.maintainhelper.Utils.LogUtils.LogInfo;

import java.util.HashMap;

public class UIParams {
    private static UIParams uiParams;
    private UIParams(){
    }
    public static UIParams getParamsInstance(){
        return uiParams==null?uiParams =new UIParams():uiParams;
    }
    private static HashMap<String, TextView> textViewMap=new HashMap<>();
    private static HashMap<String, Button> buttonMap=new HashMap<>();
    private static HashMap<String, EditText> editTextMap=new HashMap<>();
    private static HashMap<String, ListView> listViewMap=new HashMap<>();

    public static HashMap<String, CheckBox> getCheckBoxMap() {
        return checkBoxMap;
    }

    private static HashMap<String, CheckBox> checkBoxMap=new HashMap<>();

    public static HashMap<String, TextView> getTextViewMap() {
        return textViewMap;
    }

    public static HashMap<String, Button> getButtonMap() {
        return buttonMap;
    }

    public static HashMap<String, EditText> getEditTextMap() {
        return editTextMap;
    }

    public static HashMap<String, ListView> getListViewMap() {
        return listViewMap;
    }

    public void addIT(Object obj, String str){
        if(obj instanceof EditText){
            editTextMap.put(str, (EditText) obj);
            Log.e("asd",editTextMap.get(str).toString());
        }else if(obj instanceof CheckBox){
            checkBoxMap.put(str,(CheckBox) obj);
            LogInfo.showLog(str,str,1);
        }else if (obj instanceof Button){
            buttonMap.put(str, (Button) obj);
            Log.e("asd","button");
        }else if (obj instanceof TextView){
            textViewMap.put(str, (TextView) obj);
            Log.e("asd","textview");
        }else if(obj instanceof ListView){
            listViewMap.put(str,(ListView) obj);
        }
        else {
            Log.e("asd","??????????????????????????????");
        }
    }
}
