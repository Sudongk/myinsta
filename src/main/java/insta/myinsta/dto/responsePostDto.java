package insta.myinsta.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class responsePostDto {

    private String content;
    private int likeCount;
    private String imgUrl;
    private String layout;

}
