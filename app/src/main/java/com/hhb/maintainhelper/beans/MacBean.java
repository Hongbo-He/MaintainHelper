package com.hhb.maintainhelper.beans;

public class MacBean {
    public MacBean(){}

    private String mac_code;
    private String type_code;
    private String type_name;
    private String mac_name;

    private String maintain_item;
    private String maintain_period;
    private String maintain_class;
    private String maintain_consume_time;
    private String maintain_time;
    private String maintain_type;
    private String maintain_status;

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

    public String getMaintain_item() {
        return maintain_item;
    }

    public void setMaintain_item(String maintain_item) {
        this.maintain_item = maintain_item;
    }

    public String getMaintain_period() {
        return maintain_period;
    }

    public void setMaintain_period(String maintain_period) {
        this.maintain_period = maintain_period;
    }

    public String getMaintain_class() {
        return maintain_class;
    }

    public void setMaintain_class(String maintain_class) {
        this.maintain_class = maintain_class;
    }

    public String getMaintain_consume_time() {
        return maintain_consume_time;
    }

    public void setMaintain_consume_time(String maintain_consume_time) {
        this.maintain_consume_time = maintain_consume_time;
    }

    public String getMaintain_time() {
        return maintain_time;
    }

    public void setMaintain_time(String maintain_time) {
        this.maintain_time = maintain_time;
    }

    public String getMaintain_type() {
        return maintain_type;
    }

    public void setMaintain_type(String maintain_type) {
        this.maintain_type = maintain_type;
    }

    public String getMaintain_status() {
        return maintain_status;
    }

    public void setMaintain_status(String maintain_status) {
        this.maintain_status = maintain_status;
    }

    @Override
    public String toString() {
        return "MacBean{" +
                "mac_code='" + mac_code + '\'' +
                ", type_code='" + type_code + '\'' +
                ", type_name='" + type_name + '\'' +
                ", mac_name='" + mac_name + '\'' +
                ", maintain_item='" + maintain_item + '\'' +
                ", maintain_period='" + maintain_period + '\'' +
                ", maintain_class='" + maintain_class + '\'' +
                ", maintain_consume_time='" + maintain_consume_time + '\'' +
                ", maintain_time='" + maintain_time + '\'' +
                ", maintain_type='" + maintain_type + '\'' +
                ", maintain_status='" + maintain_status + '\'' +
                '}';
    }
    //    //设备编号
//
//    CREATE TABLE MAC_CODE(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,mac_code TEXT)
//
//    //编号 型号编码 型号名称 设备名称
//    CREATE TABLE MAC_CODE(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,mac_code TEXT,type_code TEXT,type_name TEXT,mac_name TEXT)
//
//    //编号
//    CREATE TABLE MAC_CODE(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,mac_code TEXT,maintain_item TEXT,maintain_period TEXT,maintain_class TEXT,maintain_consume_time TEXT,maintain_time TEXT,maintain_type TEXT,maintain_status TEXT)

}
