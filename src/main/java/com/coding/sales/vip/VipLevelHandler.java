package com.coding.sales.vip;

import java.math.BigDecimal;

public class VipLevelHandler {

    /**
     * 积分转换成等级
     * @param score
     * @return
     */
    public static VipLevelEnum resolver(int score){
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

    /**
     * 计算应加积分
     * @param viper
     * @param afterDiscountAmt
     * @return
     */
    public static int handlerPoint(Vip viper, BigDecimal afterDiscountAmt){
        int addScore = 0;

        BigDecimal multiple = viper.getLevel().getMultiple();

        addScore += Integer.parseInt(afterDiscountAmt.multiply(multiple).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        return addScore;
    }
}
