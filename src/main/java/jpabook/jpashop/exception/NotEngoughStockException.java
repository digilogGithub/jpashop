package jpabook.jpashop.exception;

public class NotEngoughStockException extends RuntimeException {
    public NotEngoughStockException() {
        super();
    }

    public NotEngoughStockException(String message) {
        super(message);
    }

    public NotEngoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEngoughStockException(Throwable cause) {
        super(cause);
    }

  /*  protected NotEngoughStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }*/
}
