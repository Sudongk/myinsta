package insta.myinsta.exception;

public class BaseException extends RuntimeException {

    protected ErrorModel error;

    public BaseException(ErrorModel error) {
        super(error.getMsg(), null);
        this.error = error;
    }

}
