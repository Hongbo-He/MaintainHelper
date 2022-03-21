package com.hhb.maintainhelper.beans;

public class MacInfoBean {
    public MacInfoBean(){}
    public MacInfoBean(String mac_code,String  type_code,String type_name,String mac_name){
        this.mac_code=mac_code;
        this.type_code=type_code;
        this.type_name=type_name;
        this.mac_name=mac_name;
    }

    public String getMac_code() {
        return mac_code;
    }

    public void setMac_code(String mac_code) {
        this.mac_code = mac_code;
    }

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getMac_name() {
        return mac_name;
    }

    public void setMac_name(String mac_name) {
        this.mac_name = mac_name;
    }

    private String mac_code;
    private String  type_code;
    private String type_name;
    private String mac_name;

    @Override
    public String toString() {
        return "MacInfoBean{" +
                "mac_code='" + mac_code + '\'' +
                ", type_code='" + type_code + '\'' +
                ", type_name='" + type_name + '\'' +
                ", mac_name='" + mac_name + '\'' +
                '}';
    }
}
