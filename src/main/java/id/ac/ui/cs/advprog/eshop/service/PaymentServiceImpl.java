package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {

        if (!PaymentMethod.contains(method)) {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }

        Payment existingPayment = paymentRepository.findById(order.getId());

        if (existingPayment != null) {
            if (PaymentStatus.SUCCESS.getValue().equals(existingPayment.getStatus())) {
                return null;
            }
        }

        Payment newPayment = new Payment(order.getId(), method, PaymentStatus.PENDING.getValue(), paymentData);
        return paymentRepository.save(newPayment);
    }



    @Override
    public Payment setStatus(Payment payment, String status) {
        // Validate status
        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }

        Payment existingPayment = paymentRepository.findById(payment.getId());
        if (existingPayment == null) {
            throw new NoSuchElementException("Payment not found: " + payment.getId());
        }

        // Update payment status
        existingPayment.setStatus(status);
        paymentRepository.save(existingPayment);

        // Update order status if applicable
        Order order = orderRepository.findById(payment.getId());
        if (order != null) {
            String orderStatus = status.equals(PaymentStatus.SUCCESS.getValue()) ? "SUCCESS" : "FAILED";
            order.setStatus(orderStatus);
            orderRepository.save(order);
        }

        return existingPayment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
