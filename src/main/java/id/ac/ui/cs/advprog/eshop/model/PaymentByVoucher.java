package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import java.util.Map;

public class PaymentByVoucher extends Payment {

    public PaymentByVoucher(String id, String method, String status, Map<String, String> paymentData) {
        super(id, method, status, paymentData);
    }

    public PaymentByVoucher(String id, String method, Map<String, String> paymentData) {
        super(id, method, PaymentStatus.PENDING.getValue(), paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData == null || paymentData.isEmpty() || !paymentData.containsKey("voucherCode") || paymentData.get("voucherCode") == null) {
            throw new IllegalArgumentException("Invalid voucher data");
        }

        String voucherCode = paymentData.get("voucherCode");
        int digitCount = 0;

        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        super.setPaymentData(paymentData);

    }

    public void validateVoucher() {
        String voucherCode = getPaymentData().get("voucherCode");

        if (voucherCode == null) {
            return;
        }

        int digitCount = (int) voucherCode.chars().filter(Character::isDigit).count();

        if (voucherCode.length() == 16 && voucherCode.startsWith("ESHOP") && digitCount == 8) {
            super.setStatus(PaymentStatus.SUCCESS.getValue());
        } else {

            super.setStatus(PaymentStatus.REJECTED.getValue());
        }
    }

}
