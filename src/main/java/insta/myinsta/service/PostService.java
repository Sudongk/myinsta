package insta.myinsta.service;

import insta.myinsta.domain.Like;
import insta.myinsta.domain.Post;
import insta.myinsta.domain.User;
import insta.myinsta.dto.CreatePostDto;
import insta.myinsta.dto.UpdatePostDto;
import insta.myinsta.exception.NotFoundPostException;
import insta.myinsta.exception.NotFoundUserException;
import insta.myinsta.repository.LikeRepository;
import insta.myinsta.repository.PostRepository;
import insta.myinsta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    public Page<Post> getAllPost(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("POST_NO").descending());
        return postRepository.findAll(pageable);
    }

    public void createPost(String userId, CreatePostDto createPostDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId))
                .orElseThrow(
                        () -> {throw new NotFoundUserException();}
                );

        Post post = Post.setPost()
                .user(user.get())
                .content(createPostDto.getContent())
                .imgUrl(createPostDto.getImgUrl())
                .layout(createPostDto.getLayout())
                .build();

        postRepository.save(post);
    }

    public void updatePost(Long postNo, UpdatePostDto updatePostDto) {
        Optional<Post> post = postRepository.findById(postNo);
                post.ifPresentOrElse(
                        p -> p.update(updatePostDto),
                        () -> { throw new NotFoundPostException(); }
                );
    }

    public void deletePost(Long postNo) {
        postRepository.findById(postNo)
                .ifPresentOrElse(
                        p -> postRepository.deleteById(p.getPostNo()),
                        () -> { throw new NotFoundPostException(); }
                );
    }

    public Post getPost(Long postNo) {
        Optional<Post> post = Optional.ofNullable(postRepository.findById(postNo)
                .orElseThrow(() -> {
                    throw new NotFoundPostException();
                }));
        return post.get();
    }

    public void like(Long postNo, String userId) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUserId(userId)
                .orElseThrow(NotFoundUserException::new));

        Optional<Post> post = Optional.ofNullable(postRepository.findById(postNo)
                .orElseThrow(NotFoundPostException::new));

        List<Like> likeList = Optional.of(user.get())
                .map(User::getLikes)
                .get()
                .stream()
                .filter(like -> like.getPost().getPostNo().equals(postNo))
                .toList();

        if (likeList.isEmpty()) {
            likeRepository.save(new Like(user.get(), post.get()));
            post.get().setLikeCount(post.get().getLikeCount() + 1);
        } else {
            user.get().getLikes().remove(likeList.get(0));
            post.get().getLikes().remove(likeList.get(0));
            post.get().setLikeCount(post.get().getLikeCount() - 1);
        }
    }
}
