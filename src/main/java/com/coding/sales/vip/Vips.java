package com.coding.sales.vip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vips {
    public static Map<String, Vip> vipMap = new HashMap<String, Vip>();
    static {
        List<String> discountList = new ArrayList<String>();
        discountList.add("9折券");

        Vip vip1 = new Vip("马丁",VipLevelEnum.NORMAL,"6236609999",9860, discountList);
        vipMap.put(vip1.getMemberId(), vip1);

        List<String> list2 = new ArrayList<String>();
        list2.add("95折券");
        list2.add("9折券");
        Vip vip2 = new Vip("王立",VipLevelEnum.GOLD,"6630009999",48860, list2);
        vipMap.put(vip2.getMemberId(), vip2);

        Vip vip3 = new Vip("李想",VipLevelEnum.PLATINUM,"8230009999",98860,null);
        vipMap.put(vip3.getMemberId(), vip3);

        Vip vip4 = new Vip("张三",VipLevelEnum.DIAMOND,"9230009999",198860, null);
        vipMap.put(vip4.getMemberId(), vip4);
    }
}
