package insta.myinsta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CreatePostDto {

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @JsonProperty("img_url")
    @NotBlank(message = "이미지를 업로드 해주세요.")
    private String imgUrl;

    @NotBlank(message = "레이아웃 타입을 지정해주세요.")
    private String layout;
}
