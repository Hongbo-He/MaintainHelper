package com.hhb.maintainhelper.beans;

public class MacMaintainInfoBean {
    public MacMaintainInfoBean(){}
    public MacMaintainInfoBean(String maintain_item,String maintain_period,String maintain_type,String maintain_class,String maintain_status){
        this.maintain_item=maintain_item;
        this.maintain_period=maintain_period;
        this.maintain_type=maintain_type;
        this.maintain_class=maintain_class;
        this.maintain_status=maintain_status;


    }

    private String maintain_item;
    private String maintain_period;
    private String maintain_type;
    private String maintain_class;
    private String maintain_status;

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
        return "MacMaintainInfoBean{" +
                "maintain_item='" + maintain_item + '\'' +
                ", maintain_period='" + maintain_period + '\'' +
                ", maintain_type='" + maintain_type + '\'' +
                ", maintain_status='" + maintain_status + '\'' +
                '}';
    }

    public String getMaintain_class() {
        return maintain_class;
    }

    public void setMaintain_class(String maintain_class) {
        this.maintain_class = maintain_class;
    }
}
