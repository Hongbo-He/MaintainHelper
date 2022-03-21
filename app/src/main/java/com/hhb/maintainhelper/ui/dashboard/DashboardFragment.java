package com.hhb.maintainhelper.ui.dashboard;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.hhb.maintainhelper.AppContext;
import com.hhb.maintainhelper.FileOprations.QRCreater.GetNetIMG;
import com.hhb.maintainhelper.R;
import com.hhb.maintainhelper.Utils.LogUtils.LogInfo;
import com.hhb.maintainhelper.Utils.OperationParams;
import com.hhb.maintainhelper.Utils.ToastUtils.ToastInfo;
import com.hhb.maintainhelper.beans.MacBaseBean;
import com.hhb.maintainhelper.beans.MacInfoBean;
import com.hhb.maintainhelper.dao.DB_PARAMS;
import com.hhb.maintainhelper.dao.MacDaoImplement;
import com.hhb.maintainhelper.ui.UIParams;
import com.hhb.maintainhelper.ui.customdialog.MyDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardFragment extends Fragment implements View.OnLongClickListener, View.OnClickListener {

    private DashboardViewModel dashboardViewModel;
    private UIParams uiParams = UIParams.getParamsInstance();
    private String tag ="DashboardFragment";
    private String dirPath= Environment.getExternalStorageDirectory() + File.separator + "设备保养" +File.separator;
    private final String[] currentItem = new String[1];
    private MacDaoImplement macDaoImplement;
    String keys[] =new String[2];
    String values[] =new String[2];


    private int operation = OperationParams.OP_BEFORE_ONE_DAY;

    private String getStringByID(int id){
        return getResources().getString(id);
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        final TextView tv_counter = root.findViewById(R.id.frag_dashboard_tv_counter);
        uiParams.addIT(tv_counter,getStringByID(R.string.frag_dashboard_counter));

        final ListView listView = root.findViewById(R.id.frag_dashboard_base_list1);

        final CheckBox lock1 =root.findViewById(R.id.frag_dashboard_cb_lock1);
        uiParams.addIT(lock1,getStringByID(R.string.frag_dash_cb_lock1));

        final CheckBox lock2 =root.findViewById(R.id.frag_dashboard_cb_lock2);
        uiParams.addIT(lock2,getStringByID(R.string.frag_dash_cb_lock2));

        final Button restAll =root.findViewById(R.id.frag_dashboard_bt_restAll);
        uiParams.addIT(restAll,getStringByID(R.string.frag_dash_bt_restAll));
        restAll.setOnClickListener(this);
        restAll.setOnLongClickListener(this);
        final Button restToday =root.findViewById(R.id.frag_dashboard_bt_restToday);
        uiParams.addIT(restToday,getStringByID(R.string.frag_dash_bt_restToday));
        restToday.setOnClickListener(this);
        restToday.setOnLongClickListener(this);
        final Button restBefore =root.findViewById(R.id.frag_dashboard_bt_restBefore);
        uiParams.addIT(restBefore,getStringByID(R.string.frag_dash_bt_rest_before));
        restBefore.setOnClickListener(this);
        restBefore.setOnLongClickListener(this);


        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month =  Calendar.getInstance().get(Calendar.MONTH)+1;
        int day =  Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        LogInfo.showLog(tag,"---------初始化---"+day,1);

        final EditText editYear = root.findViewById(R.id.frag_dashboard_edit_year);
        uiParams.addIT(editYear,getStringByID(R.string.frag_dash_et_year));
        LogInfo.showLog(tag,(editYear==null)+"",1);
        editYear.setText(year+"");
        final EditText editMonth = root.findViewById(R.id.frag_dashboard_edit_month);
        uiParams.addIT(editMonth,getStringByID(R.string.frag_dash_et_month));
        editMonth.setText(month+"");
        final EditText editDay= root.findViewById(R.id.frag_dashboard_edit_day);
        uiParams.addIT(editDay,getStringByID(R.string.frag_dash_et_day));
        editDay.setText(day+"");

        editYear.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                initListAdapter(currentItem[0],listView);
            }
            @Override
            public void afterTextChanged(Editable s) {
                initListAdapter(currentItem[0],listView);
            }
        });
        editMonth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                initListAdapter(currentItem[0],listView);
            }

            @Override
            public void afterTextChanged(Editable s) {
                initListAdapter(currentItem[0],listView);
            }
        });
        editDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                values[0]=editYear.getEditableText().toString()+"-"+editMonth.getEditableText().toString()+"-"+editDay.getEditableText().toString();
                initListAdapter(currentItem[0],listView);
            }

            @Override
            public void afterTextChanged(Editable s) {
                values[0]=editYear.getEditableText().toString()+"-"+editMonth.getEditableText().toString()+"-"+editDay.getEditableText().toString();
                initListAdapter(currentItem[0],listView);
            }
        });

        uiParams.getParamsInstance().addIT(listView, "待保养");

        //设置下拉框的数据和适配器
        final Spinner spinner =root.findViewById(R.id.frag_dashboard_status_choose_spinner);
        //下拉框数据
        String[] statusData =AppContext.initAppContext().getContext().getResources().getStringArray(R.array.maintian_status_string);
        //下拉框监听器
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(AppContext.initAppContext().getContext(),android.R.layout.simple_spinner_item,statusData);
        //设置数据适配器
        spinner.setAdapter(adapter);

        final RadioGroup radioGroup = root.findViewById(R.id.frag_dashboard_rg_operation);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton radbtn = (RadioButton) root.findViewById(checkedId);
                switch (checkedId){
                    case  R.id.frag_dashboard_rb_today:
                        operation =OperationParams.OP_TODAY;
                        break;
                    case R.id.frag_dashboard_rb_before:
                        operation =OperationParams.OP_BEFORE_ONE_DAY;
                        break;
                    case R.id.frag_dashboard_rb_after:
                        operation =OperationParams.OP_AFTER_ONE_DAY;
                        break;
                    case R.id.frag_dashboard_rb_whole:
                        operation =OperationParams.OP_WHOLE_MONTH;
                        break;
                }
                initListAdapter(currentItem[0] ,listView);
            }
        });

        //当前状态，赋初值，避免查询时出现异常
        currentItem[0] ="待保养";

        //添加监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                ToastInfo.showToast(AppContext.initAppContext().getContext(),statusData[position]);
                initListAdapter(currentItem[0] =statusData[position],listView);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //获取下拉框里的字符串，作为查询条件
        currentItem[0] =spinner.getSelectedItem().toString();
        //更新一下列表
        initListAdapter(currentItem[0],listView);

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void getEditText(){
         EditText etYear  = uiParams.getEditTextMap().get(getStringByID(R.string.frag_dash_et_year));
         EditText etMonth  = UIParams.getEditTextMap().get(getStringByID(R.string.frag_dash_et_month));
         EditText etDay  = UIParams.getEditTextMap().get(getStringByID(R.string.frag_dash_et_day));

            ArrayList<EditText> editTexts =new ArrayList<>();
            editTexts.add(etYear);
            editTexts.add(etMonth);
            editTexts.add(etDay);
            StringBuilder stringBuilder =new StringBuilder();

        int year=0,month = 0,day=0;
        for(int i=0;i<editTexts.size();i++){
            try{
            if(i==0){
               year = Integer.parseInt(editTexts.get(i).getEditableText().toString());
               if (year>2100||year<=0){
                   year =Calendar.getInstance().get(Calendar.YEAR);
                   editTexts.get(i).setText(year+"");
                   ToastInfo.showToast(AppContext.initAppContext().getContext(),"年 输入错误，已自动设置为今天的时间");
               }
            }else if(i==1){
                    month = Integer.parseInt(editTexts.get(i).getEditableText().toString());
                    if (month>12||month<=0){
                        month =Calendar.getInstance().get(Calendar.MONTH)+1;
                        editTexts.get(i).setText(month+"");
                        ToastInfo.showToast(AppContext.initAppContext().getContext(),"月份 输入错误，已自动设置为今天的时间");
                    }
                }else if(i==2){
                    day = Integer.parseInt(editTexts.get(i).getEditableText().toString());
                     if (day>31||day<0){ day =Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                        editTexts.get(i).setText(day+"");
                        ToastInfo.showToast(AppContext.initAppContext().getContext(),"号数 输入错误，已自动设置为今天的时间");
                }
            }

            }catch (Exception e){
                ToastInfo.showToast(AppContext.initAppContext().getContext(),"日期异常，已自动设置为今天的时间");
                year =Calendar.getInstance().get(Calendar.YEAR);
                month =Calendar.getInstance().get(Calendar.MONTH)+1;
                day =Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            }
        }
        stringBuilder.append(year+"-"+month+"-"+day);
        values[0]=stringBuilder.toString();
        LogInfo.showLog(tag,"获取"+values[0],1);

    }

    private void initDialog(List datalist,int position){
         macDaoImplement =new MacDaoImplement();
        MacInfoBean macInfoBean = macDaoImplement.findMacInfoBeanByMacCode(((MacBaseBean) datalist.get(position)).getMac_code());
        LogInfo.showLog(tag+tag,"asdddddddddd",1);
        //判断有没有对应的图片
        File file =new File(dirPath+((MacBaseBean) datalist.get(position)).getMac_code()+".png");
        if(!file.exists()){
            //生成图片
            GetNetIMG getNetIMG =new GetNetIMG(((MacBaseBean) datalist.get(position)).getMac_code());
            getNetIMG.startGet();
            getNetIMG=null;
            System.gc();
        }
        MyDialog.Builder builder = new MyDialog.Builder(getContext());
        builder.setMacCode(((MacBaseBean) datalist.get(position)).getMac_code());
        builder.setMaintain_time(((MacBaseBean) datalist.get(position)).getMaintain_time());
        builder.setType_name(macInfoBean.getType_name());
        builder.setMacName(macInfoBean.getMac_name());
        builder.setImg(dirPath+((MacBaseBean) datalist.get(position)).getMac_code()+".png");

        builder.setPositiveButtonText("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //点击确定之后，把状态改变
                //改变---先获取原来的状态
                String oriStatus =((MacBaseBean) datalist.get(position)).getMaintain_status();
                String newStatus ="";
                if (oriStatus.equals("待保养")){
                    newStatus ="保养中";
                }else if(oriStatus.equals("保养中")){
                    newStatus ="已完成";
                }
                /*
                else  if(oriStatus.equals("已完成")){
                    newStatus="待保养";
                }*/
                macDaoImplement.updataStatus(((MacBaseBean) datalist.get(position)).getMac_code(),oriStatus,newStatus);
                initListAdapter(currentItem[0],uiParams.getListViewMap().get("待保养"));
                dialog.dismiss();
            }
        });
        builder.setNegativeButtonText("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        try {
            builder.create().show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void initListAdapter(String currentItem,ListView listView){
        //获取对象
        List <Map<String,Object>> fi =new ArrayList<>();
         this.macDaoImplement =new MacDaoImplement();
//        List<MacBaseBean> baseBeansList = daoImplement.find_ALL_BaseInfoByMacCode();
//        List<MacBaseBean> baseBeansList = daoImplement.find_ALL_BaseInfoByStatus(currentItem);
//        List<MacBaseBean> baseBeansList = daoImplement.findBaseInfoByDate("2022年3月16日");

        keys[0] = DB_PARAMS.MAINTAIN_TIME;
        keys[1] = DB_PARAMS.MAINTAIN_STATUS;

        getEditText();

        values[1] =currentItem;
//        List<MacBaseBean> baseBeansList = daoImplement.findBaseInfoByMultyCondition(keys,values);
        List<MacBaseBean> baseBeansList = macDaoImplement.findBaseInfoByMultyCondition(keys,values, operation);
//                daoImplement.findBaseInfoByMultyCondition_Before_Someday(keys,values);

//                daoImplement.find_ALL_BaseInfoByMacCode();

        List<MacBaseBean> datas=baseBeansList;
        for(int i=0;i<datas.size();i++){
            Map<String,Object> map1 =new HashMap<>();
            map1.put("tag",datas.get(i).getMac_code());
            map1.put("tag2",datas.get(i).getMaintain_time());
            map1.put("tag3",datas.get(i).getMaintain_status());
            fi.add(map1);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),fi,R.layout.list,
                new String[]{"tag","tag2","tag3"},
                new int[]{R.id.base_tv_mac_code,
                        R.id.base_tv_maintain_time,
                        R.id.base_tv_data_id,
                });
        uiParams.getListViewMap().get("待保养").setAdapter(simpleAdapter);

        UIParams.getTextViewMap().get(getStringByID(R.string.frag_dashboard_counter)).setText("共有 "+datas.size()+"条内容");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                initDialog(datas,position);
            }
        });


    }
    @Override
    public boolean onLongClick(View v) {
        CheckBox lock1 =UIParams.getCheckBoxMap().get(getStringByID(R.string.frag_dash_cb_lock1));
        CheckBox lock2 =UIParams.getCheckBoxMap().get(getStringByID(R.string.frag_dash_cb_lock2));
        switch (v.getId()){
            case R.id.frag_dashboard_bt_restToday:
                if (!lock1.isChecked()){
                    ToastInfo.showToast(AppContext.initAppContext().getContext(),"功能未启用，如需启用请勾选左侧第一个复选框");
                }else {
                    ToastInfo.showToast(AppContext.initAppContext().getContext(),"按钮被长按");
                    macDaoImplement.updataStatus_Finish_Today();
                    ToastInfo.showToast(AppContext.initAppContext().getContext(),"完成");
                }
                break;
            case R.id.frag_dashboard_bt_restBefore:
                if (!lock1.isChecked()){
                    ToastInfo.showToast(AppContext.initAppContext().getContext(),"功能未启用，如需启用请勾选左侧第一个复选框");
                }else {
                    ToastInfo.showToast(AppContext.initAppContext().getContext(),"按钮被长按");
                    macDaoImplement =new MacDaoImplement();
                    macDaoImplement.updataStatus_Finish_BeforeToday();
                    ToastInfo.showToast(AppContext.initAppContext().getContext(),"完成");
                }
                break;
            case R.id.frag_dashboard_bt_restAll:
                if (!lock2.isChecked()){
                    ToastInfo.showToast(AppContext.initAppContext().getContext(),"功能未启用，如需启用请勾选左侧第二个复选框");
                }else {
                    ToastInfo.showToast(AppContext.initAppContext().getContext(), "按钮被长按");
                    macDaoImplement = new MacDaoImplement();
                    macDaoImplement.restStatus_All();
                    ToastInfo.showToast(AppContext.initAppContext().getContext(), "已重置");
                }
                break;
            default: break;
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        CheckBox lock1 =UIParams.getCheckBoxMap().get(getStringByID(R.string.frag_dash_cb_lock1));
        CheckBox lock2 =UIParams.getCheckBoxMap().get(getStringByID(R.string.frag_dash_cb_lock2));
        switch (v.getId()){
            case R.id.frag_dashboard_bt_restToday:
                if (!lock1.isChecked()){
                    ToastInfo.showToast(AppContext.initAppContext().getContext(),"功能未启用，如需启用请勾选左侧第一个复选框");
                }else {
                    ToastInfo.showToast(AppContext.initAppContext().getContext(), getStringByID(R.string.frag_dash_bt_restToday) + "可用，长按生效");
                }
                break;
            case R.id.frag_dashboard_bt_restBefore:
                if (!lock1.isChecked()){
                    ToastInfo.showToast(AppContext.initAppContext().getContext(),"功能未启用，如需启用请勾选左侧第一个复选框");
                }else {
                    ToastInfo.showToast(AppContext.initAppContext().getContext(), getStringByID(R.string.frag_dash_bt_rest_before) + "可用，长按生效");
                }
                break;
                case R.id.frag_dashboard_bt_restAll:
                    if (!lock2.isChecked()){
                        ToastInfo.showToast(AppContext.initAppContext().getContext(),"功能未启用，如需启用请勾选左侧第二个复选框");
                    }else {
                        ToastInfo.showToast(AppContext.initAppContext().getContext(), getStringByID(R.string.frag_dash_bt_restAll) + "可用，长按生效");
                    }
                    break;
                default: break;
        }
    }
}