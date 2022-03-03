package insta.myinsta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    @JsonProperty("user_id")
    @NotBlank(message = "아이디를 입력해 주세요.")
    @Size(min = 3, max = 50)
    private String userId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min = 3, max = 100)
    private String password;

    @Builder
    public LoginDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
