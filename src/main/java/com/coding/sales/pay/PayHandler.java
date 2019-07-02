package com.coding.sales.pay;

import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.PaymentRepresentation;

import java.util.ArrayList;
import java.util.List;

public class PayHandler {
    public static void handler(List<PaymentRepresentation> paymentItems, List<PaymentCommand> paymentCommandList){
        for (PaymentCommand paymentCommand : paymentCommandList) {
            PaymentRepresentation paymentRepresentation = new PaymentRepresentation(paymentCommand.getType(), paymentCommand.getAmount());
            paymentItems.add(paymentRepresentation);
        }
    }
}
