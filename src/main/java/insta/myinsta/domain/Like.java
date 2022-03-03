package insta.myinsta.domain;


import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "LIKE_TABLE")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_NO", updatable = false, insertable = false)
    private Long likeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NO")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_NO")
    private Post post;

    public Like(User user, Post post) {
        Assert.notNull(user, "not null");
        Assert.notNull(post, "not null");

        this.user = user;
        this.post = post;
        user.getLikes().add(this);
        post.getLikes().add(this);
    }

}
