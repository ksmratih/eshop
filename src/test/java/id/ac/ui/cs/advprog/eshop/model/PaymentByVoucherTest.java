package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentByVoucherTest {

    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;

    @BeforeEach
    void setUp() {
        validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP1234ABC5678");

        invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALIDCODE");
    }

    @Test
    void testCreateValidVoucherPayment() {
        PaymentByVoucher payment = new PaymentByVoucher("1", PaymentMethod.BY_VOUCHER.getValue(), validVoucherData);

        assertEquals("1", payment.getId());
        assertEquals(PaymentMethod.BY_VOUCHER.getValue(), payment.getMethod());
        assertEquals(validVoucherData, payment.getPaymentData());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus()); // Should be PENDING initially
    }

    @Test
    void testCreateInvalidVoucherPayment() {
        PaymentByVoucher payment = new PaymentByVoucher("2", PaymentMethod.BY_VOUCHER.getValue(), invalidVoucherData);

        assertEquals("2", payment.getId());
        assertEquals(PaymentMethod.BY_VOUCHER.getValue(), payment.getMethod());
        assertEquals(invalidVoucherData, payment.getPaymentData());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus()); // Should remain PENDING initially
    }

    @Test
    void testValidateVoucherCodeSuccess() {
        PaymentByVoucher payment = new PaymentByVoucher("3", PaymentMethod.BY_VOUCHER.getValue(), validVoucherData);
        payment.validateVoucher();

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testValidateVoucherCodeFailure() {
        PaymentByVoucher payment = new PaymentByVoucher("4", PaymentMethod.BY_VOUCHER.getValue(), invalidVoucherData);
        payment.validateVoucher();

        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}
