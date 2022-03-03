package insta.myinsta.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import insta.myinsta.dto.UpdatePostDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_NO", updatable = false, insertable = false)
    private Long postNo;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(name="LIKE_COUNT", nullable = false)
    @ColumnDefault("0")
    private int likeCount;

    @Column(name = "IMG_URL", nullable = false, length = 300)
    private String imgUrl;

    @Column(nullable = false)
    private String layout;

    // default EAGER
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_NO")
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @Builder(builderClassName = "setPost", builderMethodName = "setPost")
    public Post(String content, int likeCount, String imgUrl, String layout, User user) {
        this.content = content;
        this.likeCount = likeCount;
        this.imgUrl = imgUrl;
        this.layout = layout;
        this.user = user;
        user.getPosts().add(this);
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void update(UpdatePostDto updatePostDto) {
        this.content = updatePostDto.getContent();
        this.imgUrl = updatePostDto.getImgUrl();
        this.layout = updatePostDto.getLayout();
    }

}
