package com.hhb.maintainhelper.dao;

public class DB_PARAMS {


    public static final String DB_NAME = "maintains.db";


    public static final String TABLE_MAC_CODE = "MAC_CODE";
    public static final String TABLE_MAC_INFO = "MAC_INFO";
    public static final String TABLE_MAC_MAINTAINS_INFO = "MAC_MAINTAINS_INFO";

    public static final String ID = "id";
    public static final String MAC_CODE = "mac_code";

    public static final String TYPE_CODE = "type_code";
    public static final String TYPE_NAME = "type_name";
    public static final String MAC_NAME = "mac_name";

    public static final String MAINTAIN_ITEM = "maintain_item";
    public static final String MAINTAIN_PERIOD = "maintain_period";
    public static final String MAINTAIN_CLASS = "maintain_class";
    public static final String MAINTAIN_CONSUME_TIME = "maintain_consume_time";
    public static final String MAINTAIN_TIME = "maintain_time";
    public static final String MAINTAIN_TYPE = "maintain_type";
    public static final String MAINTAIN_STATUS = "maintain_status";
    public static final String DATA_ID = "data_id";

    public static final String[] TABLE_MAC_CODE_ARR =new String[]{MAC_CODE,MAINTAIN_TIME,MAINTAIN_STATUS,DATA_ID};

    public static final String[] TABLE_MAC_INFO_ARR =new String[]{MAC_CODE,TYPE_CODE,TYPE_NAME,MAC_NAME,DATA_ID};
    public static final String[] TABLE_MAC_MAINTAINS_INFO_ARR =new String[]{MAC_CODE,MAINTAIN_ITEM,MAINTAIN_PERIOD,MAINTAIN_CLASS,MAINTAIN_CONSUME_TIME,MAINTAIN_TIME,MAINTAIN_TYPE,MAINTAIN_STATUS,DATA_ID};

}
