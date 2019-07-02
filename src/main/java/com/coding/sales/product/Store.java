package com.coding.sales.product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Store {
    private static Map<String, Product> productMap = new HashMap<String, Product>();
    static {
        Product product1 = new Product("001001", "世园会五十国钱币册", new BigDecimal(998), "册");
        productMap.put(product1.getProductId(), product1);

        Product product2 = new Product("001002", "2019北京世园会纪念银章大全40g", new BigDecimal(1380), "盒", null,"0.9");
        productMap.put(product1.getProductId(), product2);

        Product product3 = new Product("003001", "招财进宝", new BigDecimal(1580), "条", null,  "0.95");
        productMap.put(product1.getProductId(), product3);

        Product product4 = new Product("003002", "水晶之恋", new BigDecimal(980), "条", "第3件半价，满3减1",  null);
        productMap.put(product1.getProductId(), product4);

        Product product5 = new Product("002002", "中国经典钱币套装", new BigDecimal(998), "套", "每满2000减30，每满1000减10",  null);
        productMap.put(product1.getProductId(), product5);

        Product product6 = new Product("002001", "守扩之羽比翼双飞4.8g", new BigDecimal(1080), "条", "第3件半价，满3送1",  "0.95");
        productMap.put(product1.getProductId(), product6);

        Product product7 = new Product("002003", "中国银象棋12g", new BigDecimal(698), "套", "每满3000元减350, 每满2000减30，每满1000减10",  "0.9");
        productMap.put(product1.getProductId(), product7);
    }
}
