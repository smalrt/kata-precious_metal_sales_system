package com.coding.sales.product;

import java.math.BigDecimal;

/**
 * 商品信息
 */
public class Product {
    /**
     * 商品id
     */
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品价格
     */
    private BigDecimal price;
    private String unit;
    private String rule_full_reduction;
    private String rule_discount;

    public Product(String productId, String productName, BigDecimal price, String unit) {
        this(productId, productName, price, unit, null, null);
    }

    public Product(String productId, String productName, BigDecimal price, String unit, String rule_full_reduction, String rule_discount) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.unit = unit;
        this.rule_full_reduction = rule_full_reduction;
        this.rule_discount = rule_discount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
