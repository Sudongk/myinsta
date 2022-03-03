package insta.myinsta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePostDto {

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @JsonProperty("img_url")
    @NotBlank(message = "이미지를 업로드 해주세요.")
    private String imgUrl;

    @NotBlank(message = "레이아웃 타입을 지정해주세요.")
    private String layout;
}
