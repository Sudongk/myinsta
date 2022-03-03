package insta.myinsta.domain;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DynamicUpdate // 변경한 필드만 대응
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NO", updatable = false, insertable = false)
    private Long userNo;

    @Column(nullable = false, length = 45, unique = true)
    private String userId;

    @Column(nullable = false, length = 45)
    private String nickname;

    @Column(nullable = false, length = 45)
    private String password;

    // default Lazy
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @Builder(builderClassName = "setUser", builderMethodName = "setUser")
    public User(String userId, String nickname, String password) {
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
    }

}
