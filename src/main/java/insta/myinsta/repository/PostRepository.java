package insta.myinsta.repository;

import insta.myinsta.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    @Override
    Iterable<Post> findAll(Sort sort);

    @Override
    Page<Post> findAll(Pageable pageable);
}
