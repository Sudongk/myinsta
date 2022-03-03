package insta.myinsta.exception;

public class NotFoundPostException extends BaseException{
    public NotFoundPostException() {
        this("해당 게시글이 존재하지 않습니다.");
    }

    public NotFoundPostException(String msg) {
        this(400, msg);
    }

    public NotFoundPostException(int code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .build());
    }

}
