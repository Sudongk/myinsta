package insta.myinsta.controller;

import insta.myinsta.domain.Post;
import insta.myinsta.dto.CreatePostDto;
import insta.myinsta.dto.UpdatePostDto;
import insta.myinsta.exception.UnknownUserAccessException;
import insta.myinsta.security.SecurityUtil;
import insta.myinsta.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/api/post") // requestBody에서 page 정보 얻어서 사용
    public ResponseEntity<Page<Post>> postList(@RequestBody int page) {
        Page<Post> posts = postService.getAllPost(page);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @PostMapping("/api/post/create")
    public ResponseEntity create(@Valid @RequestBody CreatePostDto createPostDto) {

        Optional<String> currentUerId = Optional.ofNullable(SecurityUtil.getCurrentUerId()
                .orElseThrow(UnknownUserAccessException::new));

        postService.createPost(currentUerId.get(), createPostDto);
        return ResponseEntity.status(HttpStatus.OK).body("게시글 등록 완료!");
    }

    @PutMapping("/api/post/update/{postNo}")
    public ResponseEntity update(@PathVariable Long postNo,
                                 @Valid UpdatePostDto updatePostDto) {

        Optional<String> currentUerId = Optional.ofNullable(SecurityUtil.getCurrentUerId()
                .orElseThrow(UnknownUserAccessException::new));

        postService.updatePost(postNo, updatePostDto);
        return ResponseEntity.status(HttpStatus.OK).body("게시글 수정 완료!");
    }

    @DeleteMapping("/api/post/delete/{postNo}")
    public ResponseEntity delete(@PathVariable Long postNo) {

        Optional<String> currentUerId = Optional.ofNullable(SecurityUtil.getCurrentUerId()
                .orElseThrow(UnknownUserAccessException::new));

        postService.deletePost(postNo);
        return ResponseEntity.status(HttpStatus.OK).body("게시글 삭제 완료!");
    }

    @GetMapping("/api/post/getPost/{postNo}")
    public ResponseEntity getPost(@PathVariable Long postNo) {

        Optional<String> currentUerId = Optional.ofNullable(SecurityUtil.getCurrentUerId()
                .orElseThrow(UnknownUserAccessException::new));

        Post post = postService.getPost(postNo);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @GetMapping("/api/post/like/{postNo}")
    public ResponseEntity like(@PathVariable Long postNo) {

        Optional<String> currentUerId = Optional.ofNullable(SecurityUtil.getCurrentUerId()
                .orElseThrow(UnknownUserAccessException::new));

        postService.like(postNo, currentUerId.get());
        return ResponseEntity.status(HttpStatus.OK).body("성공!");
    }

}
