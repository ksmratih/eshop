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
        Payment payment = new Payment("1", "Voucher Payment", "PENDING", validVoucherData);

        assertEquals("1", payment.getId());
        assertEquals("Voucher Payment", payment.getMethod());
        assertEquals(validVoucherData, payment.getPaymentData());
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testPaymentStatusUpdateSuccess() {
        Payment payment = new Payment("2", "Voucher Payment", "PENDING", validVoucherData);
        payment.setStatus("SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidVoucher() {
        Payment payment = new Payment("3", "Voucher Payment", "PENDING", invalidVoucherData);

        // Assuming Payment class should auto-reject invalid voucher codes
        assertEquals("REJECTED", payment.getStatus());
        assertNotEquals(validVoucherData.get("voucherCode"), payment.getPaymentData().get("voucherCode"));
    }
}
