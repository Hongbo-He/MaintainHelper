package com.hhb.maintainhelper.ui.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hhb.maintainhelper.AppContext;
import com.hhb.maintainhelper.R;
import com.hhb.maintainhelper.Utils.ToastUtils.ToastInfo;
import com.hhb.maintainhelper.beans.MacMaintainInfoBean;
import com.hhb.maintainhelper.dao.MacDaoImplement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyDialog extends Dialog {
    private String tag ="MyDialog";

    public MyDialog(@NonNull Context context) {
        super(context);
    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context =AppContext.initAppContext().getContext();
        private String tag ="MyDialog.Builder";
        private String mac_code;
        private String mac_name;
        private String type_name;
        private String maintain_time;
        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public void setMaintain_time(String maintain_time) {
            this.maintain_time = maintain_time;
        }


        private String msg;
        private String imgPath="";
        private File imgFile;
//        private Context context;
        private String title;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMsg(String msg){
             this.msg=msg;
            return this;
        }

       public Builder setTitle(String title){
            this.title=title;
            return this;
       }

       public Builder setMacCode(String mac_code){
            this.mac_code=mac_code;
            return this;
       }
       public Builder setImg(String imgPath){
            this.imgPath=imgPath;
            return this;
       }
        public Builder setImg(File file){
            this.imgFile=file;
            return this;
        }
//        public Builder setImg(Drawable imgPath){
//            this.imgPath=imgPath;
//            return this;
//        }

       public Builder setMacName(String mac_name){
            this.mac_name=mac_name;
            return this;
       }
        public Builder setPositiveButtonText(String positiveButtonText, OnClickListener positiveButtonClickListener){
            this.positiveButtonText=positiveButtonText;
            this.positiveButtonClickListener=positiveButtonClickListener;
            return this;
        }

        public Builder setNegativeButtonText(String negativeButtonText, OnClickListener negativeButtonClickListener){
            this.negativeButtonText=negativeButtonText;
            this.negativeButtonClickListener=negativeButtonClickListener;
            return this;
        }

        public Builder setPositiveButtonText(int id, OnClickListener positiveButtonClickListener){
            this.positiveButtonText=(String) context.getText(id);
            this.positiveButtonClickListener=positiveButtonClickListener;
            return this;
        }
        public Builder setNegativeButtonText(int id, OnClickListener negativeButtonClickListener){
            this.negativeButtonText=(String) context.getText(id);
            this.negativeButtonClickListener=negativeButtonClickListener;
            return this;
        }

        public MyDialog create() throws FileNotFoundException {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final MyDialog dialog = new MyDialog(context, R.style.style_dia);
            View layout = inflater.inflate(R.layout.dialog_detail_layout, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            // set the dialog title
//            ((TextView) layout.findViewById(R.id.title)).setText(title);

            ((TextView) layout.findViewById(R.id.dlg_tv_mac_code)).setText(mac_code);
            ((TextView) layout.findViewById(R.id.dlg_tv_mac_name)).setText(mac_name);
            ((TextView) layout.findViewById(R.id.dlg_tv_type_name)).setText(type_name);
            ((TextView) layout.findViewById(R.id.dlg_tv_maintain_time)).setText(maintain_time);

            //listview部分
            MacDaoImplement daoImp =new MacDaoImplement();
            ArrayList<MacMaintainInfoBean> objs = daoImp.findDtlInfoByMacCodeAndDate(new String[]{this.mac_code});

            ListView lv =layout.findViewById(R.id.dlg_dtl_info_listview);
            List<Map<String,Object>> fi =new ArrayList<>();
            List<MacMaintainInfoBean> datas=objs;
            for(int i=0;i<datas.size();i++){
                Map<String,Object> map1 =new HashMap<>();
                map1.put("tag",datas.get(i).getMaintain_item());
                map1.put("tag2",datas.get(i).getMaintain_period());
                map1.put("tag3",datas.get(i).getMaintain_type());
                map1.put("tag4",datas.get(i).getMaintain_class());
                map1.put("tag5",datas.get(i).getMaintain_status());
                fi.add(map1);
            }

            SimpleAdapter simpleAdapter = new SimpleAdapter(AppContext.initAppContext().getContext(),fi,R.layout.dlg_listview_detail_info_item,
                    new String[]{"tag","tag2","tag3","tag4","tag5"},
                    new int[]{R.id.dlg_dtl_tv_maintain_item,
                            R.id.dlg_dtl_tv_maintain_period,
                            R.id.dlg_dtl_tv_maintain_type,
                            R.id.dlg_dtl_tv_maintain_class,
                            R.id.dlg_dtl_tv_maintain_status});
            lv.setAdapter(simpleAdapter);

            if(!"".equals(imgPath)&&new File(imgPath).exists()&&new File(imgPath).isFile()){
                ((ImageView) layout.findViewById(R.id.img_qr)).setImageBitmap(new BitmapFactory().decodeFile(imgPath));
            }else {
                Bitmap bitmap =BitmapFactory.decodeStream(context.getResources().openRawResource(R.raw.mig));

            try {
                if (!imgPath.equals("")) {
                    File file2 = new File(imgPath);
                    if (file2.exists()) {
                        bitmap = BitmapFactory.decodeStream(new FileInputStream(file2));
                    } else {
                        ToastInfo.showToast(context,"图片路径不存在: "+file2.getPath());
                    }
                } else {
                    ToastInfo.showToast(context,"图片路径为空");
                }}catch (Exception e){
                    e.toString();
            }

                if (imgFile != null && imgFile.exists()) {
                    bitmap = BitmapFactory.decodeStream(new FileInputStream(imgFile));
                } else if (imgFile != null) {
                    ToastInfo.showToast(context,"图片路径异常，图片可能不存在");
                } else {
                    ToastInfo.showToast(context,"图片路径异常，图片对象空指针");
                }

                ((ImageView) layout.findViewById(R.id.img_qr)).setImageBitmap(bitmap);
                ((ImageView) layout.findViewById(R.id.img_qr)).setVisibility(View.VISIBLE);
                bitmap=null;
            }


            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.bt_confirm))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.bt_confirm))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.bt_cancel).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.bt_cancel))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.bt_cancel))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.bt_cancel).setVisibility(
                        View.GONE);
            }
            // set the content message
            if (msg != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(msg);
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((LinearLayout) layout.findViewById(R.id.content))
                        .removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content))
                        .addView(contentView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
            }
            dialog.setContentView(layout);
            return dialog;
        }

    }



}
