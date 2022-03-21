package com.hhb.maintainhelper.FileOprations.QRCreater;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MyRunnable implements Runnable {
    private String content;
    private InputStream inputStream;
    FileOutputStream outputStream;
    public MyRunnable(String content){
        this.content = content;
    }

    @Override
    public void run() {
//        getByNet();
        getByLocal();

    }

    //使用

    private void getByLocal(){
        File floder;
        String path = Environment.getExternalStorageDirectory()+ File.separator+"设备保养"+File.separator;
        floder =new File(path);
        if (!floder.exists()) floder.mkdirs();
        File file =new File(path+content);
        try {
        outputStream = new FileOutputStream(new File(path+content+".png"));
        QRCodeWriter qrCodeWriter =new QRCodeWriter();
            int w=250,h=250;
            BitMatrix bitMatrix =qrCodeWriter.encode(content, BarcodeFormat.QR_CODE,w,h);
            int[] pixels = new int[w * h];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < h; y++)
            {
                for (int x = 0; x < w; x++)
                {
                    if (bitMatrix.get(x, y))
                    {
                        pixels[y * w + x] = 0xff000000;
                    }
                    else
                    {
                        pixels[y * w + x] = 0xffffffff;
                    }
                }
            }

            Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);

            outputStream.flush();
            outputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getByNet(){
        String urlString="https://api.pwmqr.com/qrcode/create/?url=";
        try {
            urlString+=URLEncoder.encode(content,"UTF-8");
            urlString+="&down=1";

            HttpURLConnection connection = (HttpURLConnection) (new URL(urlString)).openConnection();
            if (connection!=null && connection.getResponseCode()!=200){
                Log.v("Runnable",connection.getResponseCode()+"");
            }
            inputStream = connection.getInputStream();


            String path = Environment.getExternalStorageDirectory()+ File.separator+"设备保养"+File.separator;
            new File(path).mkdirs();
            File file =new File(path+content);
            outputStream = new FileOutputStream(new File(path+content+".png"));

            byte[]  buffer =new byte[2048];
            int content =0;
            while((content=inputStream.read(buffer))!=-1){
                outputStream.write(buffer, 0, content);

            }
            outputStream.close();
            inputStream.close();
            connection.disconnect();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void mkPNG(String filename) throws IOException {
        //                /storage/emulated/0

        String path = Environment.getExternalStorageDirectory()+ File.separator+"设备保养"+File.separator;
        new File(path).mkdirs();
        File file =new File(path+filename);
        if (!file.exists()){
            file.createNewFile();
        }else {
//            Toast.makeText(getContext(),file.getName()+"文件已存在",Toast.LENGTH_SHORT).show();
        }
    }
}
