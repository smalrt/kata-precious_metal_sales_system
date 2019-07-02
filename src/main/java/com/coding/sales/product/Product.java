package com.coding.sales.product;

import com.coding.sales.discount.Discount;

import java.math.BigDecimal;
import java.util.List;

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
    /**
     * 单位
     */
    private String unit;
    /**
     * 满减规则
     */
    private List<String> rule_full_reduction;
    /**
     * 优惠规则
     */
    private String discountId;

    public Product() {
    }

    public Product(String productId, String productName, BigDecimal price, String unit) {
        this(productId, productName, price, unit, null, null);
    }

    public Product(String productId, String productName, BigDecimal price, String unit, List<String> rule_full_reduction, String discountId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.unit = unit;
        this.rule_full_reduction = rule_full_reduction;
        this.discountId = discountId;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<String> getRule_full_reduction() {
        return rule_full_reduction;
    }

    public void setRule_full_reduction(List<String> rule_full_reduction) {
        this.rule_full_reduction = rule_full_reduction;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }
}
