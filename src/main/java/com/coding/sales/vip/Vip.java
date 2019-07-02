package com.coding.sales.vip;

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
    private Long score;

    public Vip(String name, VipLevelEnum level, String memberId, Long score) {
        this.name = name;
        this.level = level;
        this.memberId = memberId;
        this.score = score;
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

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
