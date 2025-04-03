package io.gffd94.greppapi.controller;

import io.gffd94.greppapi.dto.SaveRequest;
import io.gffd94.greppapi.dto.UpdateRequest;
import io.gffd94.greppapi.entity.Post;
import io.gffd94.greppapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
// @Controller
//@ResponseBody
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;

    // 응답코드 100 ~ 500

    @GetMapping("/{postId}")
//    @ResponseStatus(HttpStatus.OK) // 200
    public ResponseEntity<Post> findById(@PathVariable Long postId)
    {
        Post findPost = postRepository.findById(postId);
//        return findPost;
//        return ResponseEntity.status(HttpStatus.OK).body(findPost);
        return ResponseEntity.ok(findPost);
    }


    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED) //201
    public ResponseEntity<Long> save(@RequestBody SaveRequest request){
        Post post = Post.of(request);
        Post savedPost = postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost.getId());

    }

    //Patch
    @PatchMapping("/{postId}")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Post> update(@PathVariable Long postId, @RequestBody UpdateRequest request) {
        Post update = postRepository.update(postId, request);
        return ResponseEntity.ok(update);
    }

    //Delete
    @DeleteMapping("/{postId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long postId) {
        postRepository.remove(postId);
        return ResponseEntity.noContent().build();
    }

}
