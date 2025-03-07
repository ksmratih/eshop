package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import java.util.Map;

public class PaymentByTransfer extends Payment {
    public PaymentByTransfer(String id, String method, String status, Map<String, String> paymentData) {
        super(id, method, status, paymentData);
    }

    public PaymentByTransfer(String id, String method, Map<String, String> paymentData) {
        super(id, method, PaymentStatus.PENDING.getValue(), paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData == null || paymentData.isEmpty() ||
                !paymentData.containsKey("bankName") || paymentData.get("bankName").isEmpty() ||
                !paymentData.containsKey("referenceCode") || paymentData.get("referenceCode").isEmpty()) {
            super.setStatus(PaymentStatus.REJECTED.getValue());
        } else {
            super.setPaymentData(paymentData);
            super.setStatus(PaymentStatus.SUCCESS.getValue());
        }
    }
}