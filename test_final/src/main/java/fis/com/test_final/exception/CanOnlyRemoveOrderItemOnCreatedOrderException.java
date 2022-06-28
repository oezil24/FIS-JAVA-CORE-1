package fis.com.test_final.exception;

public class CanOnlyRemoveOrderItemOnCreatedOrderException extends Exception {
    public CanOnlyRemoveOrderItemOnCreatedOrderException(String message) {
        super(message);
    }
}
