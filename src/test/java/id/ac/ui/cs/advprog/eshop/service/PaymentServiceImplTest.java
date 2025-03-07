package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository;

    private List<Payment> payments;
    private Order order;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();
        order = new Order("order-123", new ArrayList<>(), 1709560000L, "John Doe");

        Map<String, String> validVoucherData = new HashMap<>();
        validVoucherData.put("voucherCode", "ESHOP12345678");

        Payment payment1 = new Payment("p1", PaymentMethod.BY_VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), validVoucherData);
        Payment payment2 = new Payment("p2", PaymentMethod.BY_TRANSFER.getValue(), PaymentStatus.PENDING.getValue(), new HashMap<>());
        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testAddPaymentIfAlreadySuccess() {
        Payment payment = payments.get(0);
        when(paymentRepository.findById(payment.getId())).thenReturn(payment);

        assertNull(paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData()));
        verify(paymentRepository, times(0)).save(payment);
    }

    @Test
    void testUpdateInvalidStatus() {
        Payment payment = payments.get(0);
        when(paymentRepository.findById(payment.getId())).thenReturn(payment);

        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, "INVALID"));
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testUpdateNonexistentPayment() {
        when(paymentRepository.findById("nonexistent")) .thenReturn(null);

        assertThrows(NoSuchElementException.class, () -> paymentService.setStatus(new Payment("nonexistent", "BY_TRANSFER", "REJECTED", new HashMap<>()), "SUCCESS"));
    }

    @Test
    void testGetPaymentById() {
        Payment payment = payments.get(0);
        when(paymentRepository.findById(payment.getId())).thenReturn(payment);

        Payment result = paymentService.getPayment(payment.getId());
        assertNotNull(result);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentByNonexistentId() {
        when(paymentRepository.findById("unknown")) .thenReturn(null);
        assertNull(paymentService.getPayment("unknown"));
    }

    @Test
    void testGetAllPayments() {
        when(paymentRepository.findAll()).thenReturn(payments);

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(payments.size(), results.size());
    }
}