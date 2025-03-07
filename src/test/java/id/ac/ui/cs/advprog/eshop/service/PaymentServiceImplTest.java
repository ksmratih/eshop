package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceImplTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository;

    private Order order;
    private Map<String, String> validBankData;
    private Map<String, String> invalidBankData;
    private Payment validPayment;
    private Payment invalidPayment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample order setup
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setProductId("P001");
        product.setProductName("Sample Product");
        product.setProductQuantity(1);
        productList.add(product);

        order = new Order("O001", productList, System.currentTimeMillis(), "customer123");

        // Bank payment data (valid and invalid)
        validBankData = new HashMap<>();
        validBankData.put("bankName", "Bank Example");
        validBankData.put("referenceCode", "ABC123456789");

        invalidBankData = new HashMap<>();
        invalidBankData.put("bankName", "UnknownBank");
        invalidBankData.put("referenceCode", "INVALIDCODE");

        // Sample payments
        validPayment = new Payment("PAY-001", PaymentMethod.BY_TRANSFER.getValue(), PaymentStatus.PENDING.getValue(), validBankData);
        invalidPayment = new Payment("PAY-002", PaymentMethod.BY_TRANSFER.getValue(), PaymentStatus.REJECTED.getValue(), invalidBankData);
    }

    @Test
    void testAddPaymentSuccess() {
        doReturn(validPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, PaymentMethod.BY_TRANSFER.getValue(), validBankData);

        assertNotNull(result);
        assertEquals(PaymentStatus.PENDING.getValue(), result.getStatus()); // Ensure initial status is PENDING
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentIfAlreadySuccess() {

        Payment existingSuccessfulPayment = new Payment("PAY-001", PaymentMethod.BY_TRANSFER.getValue(), PaymentStatus.SUCCESS.getValue(), validBankData);
        doReturn(existingSuccessfulPayment).when(paymentRepository).findById(order.getId());

        Payment result = paymentService.addPayment(order, PaymentMethod.BY_TRANSFER.getValue(), validBankData);

        assertNull(result);

        verify(paymentRepository, never()).save(any(Payment.class));
    }



    @Test
    void testSetPaymentStatusSuccess() {
        doReturn(validPayment).when(paymentRepository).findById(validPayment.getId());
        doReturn(validPayment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.setStatus(validPayment, PaymentStatus.SUCCESS.getValue());

        assertNotNull(result);
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetPaymentStatusInvalid() {
        doReturn(validPayment).when(paymentRepository).findById(validPayment.getId());

        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(validPayment, "INVALID"));
        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPaymentById() {
        doReturn(validPayment).when(paymentRepository).findById("PAY-001");

        Payment result = paymentService.getPayment("PAY-001");

        assertNotNull(result);
        assertEquals("PAY-001", result.getId());
        verify(paymentRepository, times(1)).findById("PAY-001");
    }

    @Test
    void testGetPaymentByIdNotFound() {
        doReturn(null).when(paymentRepository).findById("NON_EXISTENT");

        Payment result = paymentService.getPayment("NON_EXISTENT");

        assertNull(result);
        verify(paymentRepository, times(1)).findById("NON_EXISTENT");
    }

    @Test
    void testGetAllPayments() {
        doReturn(List.of(validPayment, invalidPayment)).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(2, result.size());
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    void testGetAllPaymentsEmpty() {
        doReturn(Collections.emptyList()).when(paymentRepository).findAll();

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(0, result.size());
        verify(paymentRepository, times(1)).findAll();
    }
}
