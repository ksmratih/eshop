package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentByTransferTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testValidPaymentData() {
        this.paymentData.put("bankName", "BNI Digital");
        this.paymentData.put("referenceCode", "xyt8290acd3567mnop4215qrst");
        PaymentByTransfer payment = new PaymentByTransfer("a71f2b3c-5d6e-47f8-9a1b-2c3d4e5f6a7b", PaymentMethod.BY_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidEmptyBankNamePaymentData() {
        this.paymentData.put("bankName", "");
        this.paymentData.put("referenceCode", "rf7821pq35xy9012mn4567abcd");
        PaymentByTransfer payment = new PaymentByTransfer("b82c3d4e-5f6a-78b9-c0d1-e2f3a4b5c6d7", PaymentMethod.BY_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidEmptyCodePaymentData() {
        this.paymentData.put("bankName", "Mandiri");
        this.paymentData.put("referenceCode", "");
        PaymentByTransfer payment = new PaymentByTransfer("c93d4e5f-6a7b-8c9d-0e1f-2a3b4c5d6e7f", PaymentMethod.BY_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidEmptyPaymentData() {
        PaymentByTransfer payment = new PaymentByTransfer("d04e5f6a-7b8c-9d0e-1f2a-3b4c5d6e7f8a", PaymentMethod.BY_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}
