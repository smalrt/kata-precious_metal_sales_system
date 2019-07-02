package com.coding.sales;

import com.coding.sales.discount.DiscountHandler;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;
import com.coding.sales.pay.PayHandler;
import com.coding.sales.product.Product;
import com.coding.sales.product.Store;
import com.coding.sales.vip.Vip;
import com.coding.sales.vip.VipLevelEnum;
import com.coding.sales.vip.VipLevelHandler;
import com.coding.sales.vip.Vips;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            discountTotalAmt = discountTotalAmt.add(DiscountHandler.handler(discountItems, viper, product, productCount));

        }

        //校验上送支付金额是否与计算优惠后总金额相同
        //打印-付款信息
        List<PaymentRepresentation> paymentItems = new ArrayList<PaymentRepresentation>();
        List<PaymentCommand> paymentCommandList = command.getPayments();
        PayHandler.handler(paymentItems, paymentCommandList);

        //计算新增积分
        int addScore = VipLevelHandler.handlerPoint(viper, totalAmt.subtract(discountTotalAmt));

        //新的积分
        int newScore = viper.getScore() + addScore;
        //新的等级
        VipLevelEnum newVipLevel = VipLevelHandler.resolver(newScore);

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
