package com.hhb.maintainhelper.FileOprations.QRCreater;

public class GetNetIMG {
    private  String content;
    public GetNetIMG(String content){
        this.content=content;
    }

    public void startGet(){
        MyRunnable myRunnable=new MyRunnable(this.content);
        Thread thread =new Thread(myRunnable);
        thread.start();
    }


}
