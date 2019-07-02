package com.coding.sales.vip;

import java.util.List;

public class Vip {
    /**
     * 姓名
     */
    private String name;
    /**
     * 等级
     */
    private VipLevelEnum level;
    /**
     * 卡号
     */
    private String memberId;
    /**
     * 积分
     */
    private Integer score;
    /**
     * 打折券 id-数量
     */
    private List<String> discountList;

    public Vip() {
    }

    public Vip(String name, VipLevelEnum level, String memberId, Integer score, List<String> discountList) {
        this.name = name;
        this.level = level;
        this.memberId = memberId;
        this.score = score;
        this.discountList = discountList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VipLevelEnum getLevel() {
        return level;
    }

    public void setLevel(VipLevelEnum level) {
        this.level = level;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<String> getDiscountList() {
        return discountList;
    }

    public void setDiscountList(List<String> discountList) {
        this.discountList = discountList;
    }
}
