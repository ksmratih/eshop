package id.ac.ui.cs.advprog.eshop.model;

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
        Payment payment = new Payment("1", "by-voucher", "PENDING", validVoucherData);

        assertEquals("1", payment.getId());
        assertEquals("by-voucher", payment.getMethod());
        assertEquals(validVoucherData, payment.getPaymentData());
        assertEquals("PENDING", payment.getStatus()); // ✅ Now "PENDING" is allowed
    }

    @Test
    void testPaymentStatusUpdateSuccess() {
        Payment payment = new Payment("2", "by-voucher", "PENDING", validVoucherData);
        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidVoucher() {
        Payment payment = new Payment("3", "by-voucher", "PENDING", invalidVoucherData);

        // ✅ Payment should remain "PENDING" initially and not throw an error
        assertEquals("PENDING", payment.getStatus());
        assertNotEquals(validVoucherData.get("voucherCode"), payment.getPaymentData().get("voucherCode"));
    }

    @Test
    void testInvalidStatusThrowsException() {
        Payment payment = new Payment("4", "by-voucher", "PENDING", validVoucherData);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("INVALID_STATUS");
        });

        assertEquals("Invalid status: INVALID_STATUS", exception.getMessage());
    }

    @Test
    void testInvalidMethodThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Payment("5", "invalid-method", "PENDING", validVoucherData);
        });

        assertEquals("Invalid method: invalid-method", exception.getMessage());
    }
}
