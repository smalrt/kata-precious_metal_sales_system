package com.coding.sales.vip;

import java.math.BigDecimal;

public class VipLevelResolver {

    /**
     * 积分转换成等级
     * @param score
     * @return
     */
    public VipLevelEnum resolver(Long score){
        if (score < 10000) {
            return VipLevelEnum.NORMAL;
        } else if (score < 50000) {
            return VipLevelEnum.GOLD;
        } else if (score < 100000) {
            return VipLevelEnum.PLATINUM;
        } else{
            return VipLevelEnum.DIAMOND;
        }
    }
}
