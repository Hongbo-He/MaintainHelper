package com.hhb.maintainhelper.ui.home;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hhb.maintainhelper.AppContext;
import com.hhb.maintainhelper.R;
import com.hhb.maintainhelper.Utils.LogUtils.LogInfo;
import com.hhb.maintainhelper.Utils.ToastUtils.ToastInfo;
import com.hhb.maintainhelper.dao.DB_PARAMS;
import com.hhb.maintainhelper.dao.MacDaoImplement;
import com.hhb.maintainhelper.ui.UIParams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private String tag = "HomeFragment";
    private UIParams uiParams = UIParams.getParamsInstance();

    private String getStringByID(int id){
        return getResources().getString(id);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        final TextView tv_msg =root.findViewById(R.id.textview_msg);
        uiParams.addIT(tv_msg,getStringByID(R.string.tv_msg));

        final TextView tv_msg2 =root.findViewById(R.id.textview_err_msg2);
        uiParams.addIT(tv_msg2,getStringByID(R.string.tv_msg2));

        final Button bt_import =root.findViewById(R.id.button_import);
        uiParams.addIT(bt_import,getStringByID(R.string.bt_import));
        bt_import.setOnClickListener(this);

        final Button bt_export =root.findViewById(R.id.button_export);
        uiParams.addIT(bt_export,getStringByID(R.string.bt_export));
        bt_export.setOnClickListener(this);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_import:

                File floder =new File(Environment.getExternalStorageDirectory()+ File.separator+"设备保养"+File.separator);
                if(!floder.exists()){
                    floder.mkdirs();
                }
                //导入txt文本
                File file =new File(Environment.getExternalStorageDirectory()+ File.separator+"设备保养"+File.separator+"data.txt");
                BufferedReader bufferedReader = null;
                MacDaoImplement implement =new MacDaoImplement();
//                LogInfo.showLog(tag,file.getPath(),1);
                if(!file.exists()){
//                    LogInfo.showLog(tag,file.getPath()+"不存在",1);
                }else {
                    try {
                        String str ="";
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).setText("");
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg)).setText(str);
                        int count=0;
                        bufferedReader =new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
                        while ((str=bufferedReader.readLine())!=null){

                            if(str.contains("计划单号")){continue;}
                            count++;
 //        MP220210903	C0694	MOD-B024	DH-IV	高速倒邊機	晶片生產部車間		P1-1F晶片車間	LIT-EQU502-105-05.03	皮帶檢查;用時:0.5H;週期:月保养	月	二級保養	30	2022年3月17日	计划性	鄭王建	2022年3月17日 tt 08:15:26	2022年3月17日 tt 16:45:12	已完成	510		2022年2月8日 tt 00:02:19	鄭王建[C02426]	2022年3月17日 tt 16:45:42
//        MP220210902	C0694	MOD-B024	DH-IV	高速倒邊機	晶片生產部車間		P1-1F晶片車間	LIT-EQU502-105-05.01	軸承加潤滑油;用時:0.5H;週期:月保养	月	二級保養	30	2022年3月17日	计划性	鄭王建	2022年3月17日 tt 08:15:26	2022年3月17日 tt 16:45:11	已完成	510		2022年2月8日 tt 00:02:19	鄭王建[C02426]	2022年3月17日 tt 16:45:41
//        MP220210780	C0253	MOD-B020	AK-30	高速倒邊機	晶片生產部車間		P1-1F晶片車間	LIT-EQU502-105-01.03	皮帶檢查;用時:0.5H;週期:月保养	月	二級保養	30	2022年3月17日	计划性	鄭王建	2022年3月17日 tt 08:15:46	2022年3月17日 tt 16:44:38	已完成	509		2022年2月8日 tt 00:02:14	鄭王建[C02426]	2022年3月17日 tt 16:45:08
//        MP220210781	C0253	MOD-B020	AK-30	高速倒邊機	晶片生產部車間		P1-1F晶片車間	LIT-EQU502-105-01.04	軸承螺絲檢查;用時:0.5H;週期:月保养	月	二級保養	30	2022年3月17日	计划性	鄭王建	2022年3月17日 tt 08:15:46	2022年3月17日 tt 16:44:38	已完成	509		2022年2月8日 tt 00:02:14	鄭王建[C02426]	2022年3月17日 tt 16:45:08
//        0              1          2           3       4           5           6           7                   8                        9                  10       11  12         13       14      15                  16                          17             18       19  20                21           22                         23
                            if (str.equals("\\n")||str.equals("")||str.equals("\\t")||str.equals("\\r")||str.equals("\\r\\n")||str.equals(" ")){ continue;}
                            //C1323	MOD-B024	DH-IV	高速倒邊機	軸承加潤滑油;用時:0.5H;週期:月保养	月	二級保養	30	2022年3月30日	计划性	待保养
                            String [] s_All= str.split("\\t");
                            String [] s =new String[11];
                                    s[0] = s_All[1]; //code
                                    s[1] = s_All[2]; //typecode
                                    s[2] = s_All[3];  //typename
                                    s[3] = s_All[4];//macname
                                    s[4] = s_All[9];//maintitem
                                    s[5] = s_All[10];
                                    s[6] = s_All[11];
                                    s[7] = s_All[12];
                                    s[8] = s_All[13];
                                    s[9] = s_All[14];
                                    s[10] = s_All[18];
                            UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg)).append(str);
                            UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg)).append("\r\n");
                            UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg)).append("\r\n");
//                            UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg)).append("----\r\n");
                            LogInfo.showLog(tag,str,1);
                            String s1[]=new String[4];
                            String s2[]=new String[DB_PARAMS.TABLE_MAC_INFO_ARR.length];
                            String s3[]=new String[DB_PARAMS.TABLE_MAC_MAINTAINS_INFO_ARR.length];

                            s3[0]=s[0];
                            s3[1]=s[4];
                            s3[2]=s[5];
                            s3[3]=s[6];
                            s3[4]=s[7];
                            s3[5]=(s[8].replace("年","-")).replace("月","-").replace("日","");
                            s3[6]=s[9];
                            s3[7]=s[10];
                            s3[8]=s_All[0];

                            s1[0]=s_All[1];
                            s1[1]=(s_All[13].replace("年","-")).replace("月","-").replace("日","");
                            s1[2]=s_All[18];
                            s1[3]=s_All[0];

                            for(int i=0;i<s2.length-1;i++){
                                s2[i]=s[i];
                            }
                            s2[DB_PARAMS.TABLE_MAC_INFO_ARR.length-1]=s_All[0];

//                            String temp =s[8].replace("年","");
//                            temp=temp.replace("月","");
//                            temp=temp.replace("日","");
                            implement.fileToDB(DB_PARAMS.TABLE_MAC_CODE,DB_PARAMS.TABLE_MAC_CODE_ARR,s1);
                            implement.fileToDB(DB_PARAMS.TABLE_MAC_INFO,DB_PARAMS.TABLE_MAC_INFO_ARR,s2);
                            implement.fileToDB_spec(DB_PARAMS.TABLE_MAC_MAINTAINS_INFO,DB_PARAMS.TABLE_MAC_MAINTAINS_INFO_ARR,s3,1);

                            //insert or ignore into table_name (id,type) values (2,0);
                        }

                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("成功，共导入"+count+"条数据\r\n");
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("此处的数据条数并不是写入数据库的数目，而是根据文档行数判断，\r\n");
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("具体是否入数据库通过保养单号进行判断，重复的不会添加。\r\n");





                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("编码错误\r\n");
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append(e.toString());
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("\r\n");
                    } catch (FileNotFoundException e) {
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("导入的文件没找到\r\n");
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("**注意** 文件的完整路径在 "+file.getPath()+"\r\n");
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("若本机没有自行创建目录，可以手动创建目录并将txt文档放入其中 "+file.getPath()+"\r\n");
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append(e.toString());
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("\r\n");
                        e.printStackTrace();
                    } catch (IOException e) {
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("IO流读写错误，文件可能被其他程序打开，请关闭后再试\r\n");
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append(e.toString());
                        UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("\r\n");
                        e.printStackTrace();
                    } finally {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("bufferReader关闭失败\r\n");
                            UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append(e.toString());
                            UIParams.getTextViewMap().get(getStringByID(R.string.tv_msg2)).append("\r\n");
                            e.printStackTrace();
                            LogInfo.showLog(tag,"bufferReader关闭失败",1);
                        }
                    }
                }

                break;
            case R.id.button_export:
                ToastInfo.showToastLong(AppContext.initAppContext().getContext(),"暂时还没写对应的方法");
                break;
            default:
                break;

        }
    }
}