package com.coding.sales.discount;

import java.math.BigDecimal;

public class Discount {
    /**
     * 打折券id
     */
    private String id;
    /**
     * 打折券名称
     */
    private String name;
    /**
     * 打折券折扣
     */
    private BigDecimal value;

    public Discount() {
    }

    public Discount(String id, String name, BigDecimal value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
