package fis.com.test_final.exception;

public class CanNotDeleteCancelledStatusOrderException extends Exception {
    public CanNotDeleteCancelledStatusOrderException(String message) {
        super(message);
    }
}
