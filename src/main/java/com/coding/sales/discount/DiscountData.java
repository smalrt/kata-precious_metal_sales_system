package com.coding.sales.discount;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DiscountData {
    public static Map<String, Discount> discountMap = new HashMap<String, Discount>();
    static {
        Discount discount1 = new Discount("001", "9折券", new BigDecimal(0.9));
        discountMap.put("9折券", discount1);

        Discount discount2 = new Discount("002", "95折券", new BigDecimal(0.95));
        discountMap.put("95折券", discount2);
    }
}
