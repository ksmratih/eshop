package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import java.util.Arrays;
import java.util.Map;

@Getter
@Setter
@Builder
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.paymentData = paymentData;
        this.setStatus(status);
        this.setMethod(method);
    }

    public void setStatus(String status) {
        // ✅ Allowing "PENDING" as a valid status
        String[] statusList = {"SUCCESS", "REJECTED", "PENDING"};
        if (Arrays.stream(statusList).noneMatch(item -> item.equals(status))) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        this.status = status;
    }

    public void setMethod(String method) {
        String[] methodList = {"by-voucher", "by-transfer"};
        if (Arrays.stream(methodList).noneMatch(item -> item.equals(method))) {
            throw new IllegalArgumentException("Invalid method: " + method);
        }
        this.method = method;
    }
}
