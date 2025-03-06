package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class Payment {
    private String id;
    private PaymentMethod method;
    private PaymentStatus status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.paymentData = paymentData;
        this.setStatus(status);
        this.setMethod(method);
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = PaymentStatus.fromString(status);
        } else {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
    }

    public void setMethod(String method) {
        if (PaymentMethod.contains(method)) {
            this.method = PaymentMethod.fromString(method);
        } else {
            throw new IllegalArgumentException("Invalid method: " + method);
        }
    }

    public String getMethod() {
        return method.toString();  // ✅ Ensures tests expect "by-voucher"
    }

    public String getStatus() {
        return status.toString();  // ✅ Ensures tests expect "SUCCESS"
    }
}
