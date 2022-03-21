package com.hhb.maintainhelper.beans;

public class MacBaseBean {
    public MacBaseBean(){}

    public String getMac_code() {
        return mac_code;
    }

    public void setMac_code(String mac_code) {
        this.mac_code = mac_code;
    }

    public String getMaintain_status() {
        return maintain_status;
    }

    public void setMaintain_status(String maintain_status) {
        this.maintain_status = maintain_status;
    }

    public String getMaintain_time() {
        return maintain_time;
    }

    public void setMaintain_time(String maintain_time) {
        this.maintain_time = maintain_time;
    }

    public MacBaseBean(String mac_code, String maintain_status, String maintain_time){
        this.mac_code=mac_code;
        this.maintain_status=maintain_status;
        this.maintain_time=maintain_time;
    }
    private String mac_code;
    private String maintain_time;
    private String maintain_status;


    @Override
    public String toString() {
        return "MacBaseBean{" +
                "mac_code='" + mac_code + '\'' +
                ", data_id='" + maintain_status + '\'' +
                ", maintain_time='" + maintain_time + '\'' +
                '}';
    }
}
