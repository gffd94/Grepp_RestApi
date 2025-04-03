package io.gffd94.greppapi.repository;

import io.gffd94.greppapi.dto.UpdateRequest;
import io.gffd94.greppapi.entity.Post;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class PostRepository {
    // Spring Bean
    // 어떠한 값을 저장하는 것을 만드는 것을 비추!
    // 요 밑에 있는 거 처럼 ( 지금은 db가 없으므로 그냥 함)
    private Map<Long, Post> posts = new HashMap<>();

    @Getter
    private Long sequence = 0L;

    //CRUD
    public Post save(Post post) {
        sequence++;

        post.setId(sequence);
        posts.put(post.getId(), post);
        return post;
    }

    public Post findById(Long id){
        return posts.get(id);
    }

    public void remove(Long id){
        posts.remove(id);
    }

    public Post update(Long id, UpdateRequest request){
        Post findPost = posts.get(id);
        findPost.update(request);
        posts.replace(findPost.getId(), findPost);
        return findPost;
    }

}
