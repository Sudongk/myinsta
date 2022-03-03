package insta.myinsta.exception;

public class DuplicateUserException extends BaseException {

    public DuplicateUserException() {
        this("중복된 회원입니다.");
    }

    public DuplicateUserException(String msg) {
        this(400, msg);
    }

    public DuplicateUserException(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .build());
    }

}
