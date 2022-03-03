package insta.myinsta.exception;

public class NotFoundUserException extends BaseException {

    public NotFoundUserException() {
        this("존재하지 않는 회원입니다");
    }

    public NotFoundUserException(String msg) {
        this(400, msg);
    }

    public NotFoundUserException(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .build());
    }

}
