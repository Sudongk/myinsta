package insta.myinsta.exception;

public class UnknownUserAccessException extends BaseException {

    public UnknownUserAccessException() {
        this("로그인 후 사용 가능합니다.");
    }

    public UnknownUserAccessException(String msg) {
        this(400, msg);
    }

    public UnknownUserAccessException(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .build());
    }

}
