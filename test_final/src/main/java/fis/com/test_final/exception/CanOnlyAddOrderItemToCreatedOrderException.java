package fis.com.test_final.exception;

public class CanOnlyAddOrderItemToCreatedOrderException extends Exception {
    public CanOnlyAddOrderItemToCreatedOrderException(String message) {
        super(message);
    }
}
