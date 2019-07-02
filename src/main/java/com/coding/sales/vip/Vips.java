package com.coding.sales.vip;

import java.util.HashMap;
import java.util.Map;

public class Vips {
    private static Map<String, Vip> vipMap = new HashMap<String, Vip>();
    static {
        Vip vip1 = new Vip("马丁",VipLevelEnum.NORMAL,"6236609999",9860L);
        vipMap.put(vip1.getMemberId(), vip1);

        Vip vip2 = new Vip("王立",VipLevelEnum.NORMAL,"6630009999",48860L);
        vipMap.put(vip1.getMemberId(), vip2);

        Vip vip3 = new Vip("李想",VipLevelEnum.PLATINUM,"8230009999",98860L);
        vipMap.put(vip1.getMemberId(), vip3);

        Vip vip4 = new Vip("张三",VipLevelEnum.DIAMOND,"9230009999",198860L);
        vipMap.put(vip1.getMemberId(), vip4);
    }
}
