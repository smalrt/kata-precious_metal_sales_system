package com.coding.sales;

import com.coding.sales.discount.Discount;
import com.coding.sales.discount.DiscountData;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;
import com.coding.sales.product.Product;
import com.coding.sales.product.Store;
import com.coding.sales.vip.Vip;
import com.coding.sales.vip.VipLevelEnum;
import com.coding.sales.vip.VipLevelResolver;
import com.coding.sales.vip.Vips;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("参数不正确。参数1为销售订单的JSON文件名，参数2为待打印销售凭证的文本文件名.");
        }

        String jsonFileName = args[0];
        String txtFileName = args[1];

        String orderCommand = FileUtils.readFromFile(jsonFileName);
        OrderApp app = new OrderApp();
        String result = app.checkout(orderCommand);
        FileUtils.writeToFile(result, txtFileName);
    }

    public String checkout(String orderCommand) {
        OrderCommand command = OrderCommand.from(orderCommand);
        OrderRepresentation result = checkout(command);
        
        return result.toString();
    }

    OrderRepresentation checkout(OrderCommand command) {
        //会员卡号
        String memberId = command.getMemberId();

        //根据会员卡号查询会员信息
        Vip viper = Vips.vipMap.get(memberId);

        //校验会员是否有相应打折券

        //打印-订单信息
        List<OrderItemRepresentation> orderItems = new ArrayList<OrderItemRepresentation>();
        //打印-优惠信息
        List<DiscountItemRepresentation> discountItems = new ArrayList<DiscountItemRepresentation>();
        //商品合计金额
        BigDecimal totalAmt = new BigDecimal(0);
        //商品优惠金额
        BigDecimal discountTotalAmt = new BigDecimal(0);
        List<OrderItemCommand> orderList = command.getItems();
        for (OrderItemCommand orderItemCommand : orderList) {
            String productId = orderItemCommand.getProduct();
            //查询商品信息
            Product product = Store.productMap.get(productId);
            //商品单价
            BigDecimal price = product.getPrice();
            //商品数量
            BigDecimal productCount = orderItemCommand.getAmount();
            //单个商品总金额-小计
            BigDecimal singleProductTotalAmt = price.multiply(productCount);
            totalAmt = totalAmt.add(singleProductTotalAmt);

            OrderItemRepresentation orderItemRepresentation = new OrderItemRepresentation(productId, product.getProductName(), price, productCount, singleProductTotalAmt);
            orderItems.add(orderItemRepresentation);


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

            //总优惠价格
            discountTotalAmt = discountTotalAmt.add(discountAmt);

            if (discountAmt.compareTo(new BigDecimal(0)) > 0) {
                DiscountItemRepresentation discountItemRepresentation = new DiscountItemRepresentation(productId, product.getProductName(), discountAmt);
                discountItems.add(discountItemRepresentation);
            }

        }

        //校验上送支付金额是否与计算优惠后总金额相同
        //打印-付款信息
        List<PaymentRepresentation> paymentItems = new ArrayList<PaymentRepresentation>();
        List<PaymentCommand> paymentCommandList = command.getPayments();
        for (PaymentCommand paymentCommand : paymentCommandList) {
            PaymentRepresentation paymentRepresentation = new PaymentRepresentation(paymentCommand.getType(), paymentCommand.getAmount());
            paymentItems.add(paymentRepresentation);
        }

        //计算新增积分
        int addScore = 0;
        BigDecimal totalAmt_jsscore = totalAmt.subtract(discountTotalAmt);
        VipLevelEnum vipLevel = viper.getLevel();
        if (VipLevelEnum.NORMAL.equals(vipLevel)) {
            addScore += Integer.valueOf(totalAmt_jsscore.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        } else if (VipLevelEnum.GOLD.equals(vipLevel)) {
            String totalScore = totalAmt_jsscore.multiply(new BigDecimal(1.5)).toString();
            addScore += Integer.parseInt(String.valueOf(Math.ceil(Double.parseDouble(totalScore))));
        } else if (VipLevelEnum.PLATINUM.equals(vipLevel)) {
            String totalScore = totalAmt_jsscore.multiply(new BigDecimal(1.8)).toString();
            addScore += Integer.parseInt(String.valueOf(Math.ceil(Double.parseDouble(totalScore))));
        } else {
            addScore += Integer.parseInt(totalAmt_jsscore.toString()) * 2;
        }

        //新的积分
        int newScore = viper.getScore() + addScore;
        //新的等级
        VipLevelEnum newVipLevel = VipLevelResolver.resolver(newScore);

        //生成打印小票
        OrderRepresentation result = null;
        try {
            Date createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(command.getCreateTime());
            result = new OrderRepresentation(command.getOrderId(), createDate, memberId, viper.getName(), viper.getLevel().getDescr(),
                                            newVipLevel.getDescr(), addScore, newScore, orderItems, totalAmt, discountItems, discountTotalAmt, totalAmt.subtract(discountTotalAmt),
                                            paymentItems, command.getDiscounts());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //TODO: 请完成需求指定的功能

        return result;
    }
}
