package insta.myinsta.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorModel {

    private int code;
    private String msg;

    @Builder
    public ErrorModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
