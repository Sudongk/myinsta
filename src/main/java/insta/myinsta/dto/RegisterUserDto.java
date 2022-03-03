package insta.myinsta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class RegisterUserDto {

    @JsonProperty("user_id")
    @NotBlank(message = "userId 값 필요")
    @DecimalMin(value = "3", message = "3자 이상")
    @Pattern(regexp = "[a-zA-z0-9]", message = "알파벳 대소문자, 숫자로 구성")
    private String userId;

    @NotBlank(message = "nickname 값 필요")
    @DecimalMin(value = "3", message = "3자 이상")
    private String nickname;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "password 값 필요")
    @DecimalMin(value = "3", message = "3자 이상")
    private String password;

    @JsonProperty(value = "chk_password", access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String chkPassword;

    @AssertTrue(message = "password에 userId와 같은 값이 포함되면 안됩니다.")
    public boolean isPwContainId() {
        boolean result = !getPassword().contains(getUserId());
        return result;
    }

    @AssertTrue(message = "비밀번호 확인 불일치")
    public boolean isChkPwEqual() {
        boolean result = getPassword().equals(getChkPassword());
        return result;
    }
}
