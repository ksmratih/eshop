package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    BY_VOUCHER("by-voucher"),
    BY_TRANSFER("by-transfer");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.value.equals(param)) {
                return true;
            }
        }
        return false;
    }

    public static PaymentMethod fromString(String param) {
        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.value.equals(param)) {
                return method;
            }
        }
        throw new IllegalArgumentException("Invalid method: " + param);
    }

    @Override
    public String toString() {  // ✅ This ensures comparisons work
        return value;
    }
}
