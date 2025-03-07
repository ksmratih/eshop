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
            if (method.value.equalsIgnoreCase(param)) {  // Case-insensitive check
                return true;
            }
        }
        return false;
    }

    public static PaymentMethod fromString(String param) {
        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.value.equalsIgnoreCase(param)) {  // Support variations like "Transfer"
                return method;
            }
        }
        throw new IllegalArgumentException("Invalid payment method: " + param);
    }

    @Override
    public String toString() {
        return value;
    }
}
