package com.coding.sales.discount;

import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.product.Product;
import com.coding.sales.vip.Vip;

import java.math.BigDecimal;
import java.util.List;

public class DiscountHandler {
    public static BigDecimal handler(List<DiscountItemRepresentation> discountItems, Vip viper, Product product, BigDecimal productCount){
        //商品单机
        BigDecimal price = product.getPrice();
        //单个商品总金额
        BigDecimal singleProductTotalAmt = price.multiply(productCount);
        //处理优惠信息
        BigDecimal discountAmt = new BigDecimal(0);
        BigDecimal discountAmt_card = new BigDecimal(0);
        //客户优惠券
        List<String> discoutCards = viper.getDiscountList();
        String productDiscountId = product.getDiscountId();
        if (productDiscountId != null && !"".equals(productDiscountId)) {
            if (discoutCards != null && discoutCards.size() > 0) {
                for (String discountId : discoutCards) {
                    if (discountId.equals(productDiscountId)) {
                        //优惠券
                        Discount discount = DiscountData.discountMap.get(productDiscountId);
                        //使用优惠券计算优惠金额
                        discountAmt_card = singleProductTotalAmt.multiply(new BigDecimal(1).subtract(discount.getValue()));
                        break;
                    }
                }
            }
        }

        //是否满足满减规则
        BigDecimal discountAmt_manjian = new BigDecimal(0);
        List<String> rule_full_reductionList = product.getRule_full_reduction();
        if (rule_full_reductionList != null && rule_full_reductionList.size() > 0) {
            for (String rule_full_reduction : rule_full_reductionList) {
                //type|金额/件数|减金额/优惠件数
                String[] rules = rule_full_reduction.split("\\|");
                if ("0".equals(rules[0])) {//满减
                    if (singleProductTotalAmt.compareTo(new BigDecimal(rules[1])) >= 0) {
                        discountAmt_manjian = discountAmt_manjian.compareTo(new BigDecimal(rules[2])) > 0 ? discountAmt_manjian : new BigDecimal(rules[2]);
                    }
                } else if ("1".equals(rules[0])) {//按件数优惠
                    if (Integer.parseInt(productCount.toString()) >= Integer.parseInt(rules[1])) {
                        discountAmt_manjian = price.multiply(new BigDecimal(rules[2]));
                    }
                }
            }

        }

        //最优惠价格
        discountAmt = discountAmt_card.compareTo(discountAmt_manjian) > 0 ? discountAmt_card : discountAmt_manjian;

        if (discountAmt.compareTo(new BigDecimal(0)) > 0) {
            DiscountItemRepresentation discountItemRepresentation = new DiscountItemRepresentation(product.getProductId(), product.getProductName(), discountAmt);
            discountItems.add(discountItemRepresentation);
        }
        return discountAmt;
    }
}
