package insta.myinsta.repository;

import insta.myinsta.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query(value = "select DISTINCT u from User u left join fetch u.posts")
//    @Query(value = "select DISTINCT u from User u left join fetch u.likes")
//    @EntityGraph(attributePaths = "posts")
    @EntityGraph(attributePaths = "likes")
    Optional<User> findByUserId(String userId);
}
