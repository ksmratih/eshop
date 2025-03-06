package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;

    @BeforeEach
    void setUp() {
        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP1234ABC5678");

        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "MEOW");
    }

    @Test
    void testCreatePaymentWithValidVoucher() {
        Payment payment = new Payment("1", PaymentMethod.BY_VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), validVoucherData);

        assertEquals("1", payment.getId());
        assertEquals(PaymentMethod.BY_VOUCHER.getValue(), payment.getMethod());
        assertEquals(validVoucherData, payment.getPaymentData());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentStatusUpdateSuccess() {
        Payment payment = new Payment("2", PaymentMethod.BY_VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), validVoucherData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidVoucher() {
        Payment payment = new Payment("3", PaymentMethod.BY_VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), invalidVoucherData);

        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertNotEquals(validVoucherData.get("voucherCode"), payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testInvalidStatusThrowsException() {
        Payment payment = new Payment("4", PaymentMethod.BY_VOUCHER.getValue(), PaymentStatus.PENDING.getValue(), validVoucherData);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("INVALID_STATUS");
        });

        assertEquals("Invalid status: INVALID_STATUS", exception.getMessage());
    }

    @Test
    void testInvalidMethodThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Payment("5", "invalid-method", PaymentStatus.PENDING.getValue(), validVoucherData);
        });

        assertEquals("Invalid method: invalid-method", exception.getMessage());
    }
}
