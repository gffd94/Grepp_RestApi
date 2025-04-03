package io.gffd94.greppapi.entity;

import io.gffd94.greppapi.dto.SaveRequest;
import io.gffd94.greppapi.dto.UpdateRequest;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private Long id;

    private String title;
    private String contents;
    private String author;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public static Post of(SaveRequest request) {
        Post post = new Post();

        post.title = request.getTitle();
        post.contents = request.getContents();
        post.author = request.getAuthor();

        return post;
    }

    // 복수 세터 메서드
    public void update(UpdateRequest request) {
        this.title = request.getTitle();
        this.contents = request.getContents();
        this.updatedAt = LocalDateTime.now();
    }


}
