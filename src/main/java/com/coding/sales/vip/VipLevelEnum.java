package com.coding.sales.vip;

import java.math.BigDecimal;

public enum VipLevelEnum {
    /**
     * 普卡
     */
    NORMAL("NORMAL", "普卡", new BigDecimal(1)),
    /**
     * 金卡
     */
    GOLD("GOLD", "金卡", new BigDecimal(1.5)),
    /**
     * 白金卡
     */
    PLATINUM("PLATINUM", "白金卡", new BigDecimal(1.8)),
    /**
     * 钻石卡
     */
    DIAMOND("DIAMOND", "钻石卡", new BigDecimal(2));
    private String code;
    private String descr;
    private BigDecimal multiple;

    VipLevelEnum(String code, String descr, BigDecimal multiple) {
        this.code = code;
        this.descr = descr;
        this.multiple = multiple;
    }

    public String getCode() {
        return code;
    }

    public String getDescr() {
        return descr;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }
    /*public static String getDescrByCode(String code){
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
    }*/
}
