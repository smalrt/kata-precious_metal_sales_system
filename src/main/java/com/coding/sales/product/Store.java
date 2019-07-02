package com.coding.sales.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    public static Map<String, Product> productMap = new HashMap<String, Product>();
    static {
        Product product1 = new Product("001001", "世园会五十国钱币册", new BigDecimal(998), "册");
        productMap.put(product1.getProductId(), product1);

        Product product2 = new Product("001002", "2019北京世园会纪念银章大全40g", new BigDecimal(1380), "盒", null,"9折券");
        productMap.put(product2.getProductId(), product2);

        Product product3 = new Product("003001", "招财进宝", new BigDecimal(1580), "条", null,  "95折券");
        productMap.put(product3.getProductId(), product3);

        List<String> list4 = new ArrayList<String>();
        list4.add("1|3|0.5");
        list4.add("1|3|1");
        Product product4 = new Product("003002", "水晶之恋", new BigDecimal(980), "条", list4,  null);
        productMap.put(product4.getProductId(), product4);

        List<String> list5 = new ArrayList<String>();
        list5.add("0|2000|30");
        list5.add("0|1000|10");
        Product product5 = new Product("002002", "中国经典钱币套装", new BigDecimal(998), "套", list5,  null);
        productMap.put(product5.getProductId(), product5);

        Product product6 = new Product("002001", "守扩之羽比翼双飞4.8g", new BigDecimal(1080), "条", list4,  "95折券");
        productMap.put(product6.getProductId(), product6);

        List<String> list7 = new ArrayList<String>();
        list7.add("0|3000|350");
        list7.add("0|2000|30");
        list7.add("0|1000|10");
        Product product7 = new Product("002003", "中国银象棋12g", new BigDecimal(698), "套", list7,  "9折券");
        productMap.put(product7.getProductId(), product7);
    }
}
