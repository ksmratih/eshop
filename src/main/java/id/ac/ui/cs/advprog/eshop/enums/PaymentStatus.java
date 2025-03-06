package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    SUCCESS("SUCCESS"),
    REJECTED("REJECTED"),
    PENDING("PENDING");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.value.equals(param)) {
                return true;
            }
        }
        return false;
    }

    public static PaymentStatus fromString(String param) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.value.equals(param)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + param);
    }

    @Override
    public String toString() {
        return value;
    }
}
