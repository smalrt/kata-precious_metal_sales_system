package com.coding.sales.vip;

public enum VipLevelEnum {
    /**
     * 普卡
     */
    NORMAL("NORMAL", "普卡"),
    /**
     * 金卡
     */
    GOLD("GOLD", "金卡"),
    /**
     * 白金卡
     */
    PLATINUM("PLATINUM", "白金卡"),
    /**
     * 钻石卡
     */
    DIAMOND("DIAMOND", "钻石卡");
    private String code;
    private String descr;

    VipLevelEnum(String code, String descr) {
        this.code = code;
        this.descr = descr;
    }

    public String getCode() {
        return code;
    }

    public String getDescr() {
        return descr;
    }

    public static String getDescrByCode(String code){
        if (code == null) {
            return null;
        }
        VipLevelEnum enumList = getByCode(code);
        if (enumList == null) {
            return null;
        }
        return enumList.getDescr();
    }

    private static VipLevelEnum getByCode(String code) {
        for (VipLevelEnum enumList : values()) {
            if (enumList.getCode().equals(code)) {
                return enumList;
            }
        }
        return null;
    }
}
