package com.feeham.social.postfeed.controller;

import com.feeham.social.postfeed.model.dto.PostCreateDTO;
import com.feeham.social.postfeed.model.dto.PostReadDTO;
import com.feeham.social.postfeed.model.entity.Post;
import com.feeham.social.postfeed.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/internal/posts/{userId}")
    public ResponseEntity<List<Post>> getPosts(@PathVariable String userId) {
        List<Post> posts = postService.getPosts(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/public/posts/{userId}")
    public ResponseEntity<List<PostReadDTO>> readPosts(@PathVariable String userId) {
        List<PostReadDTO> posts = postService.readPosts(userId);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/public/posts")
    public ResponseEntity<String> createPost(@RequestBody PostCreateDTO postDTO) {
        boolean created = postService.createPost(postDTO);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Post created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create post");
        }
    }

    @PutMapping("/public/posts/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable UUID postId, @RequestBody PostCreateDTO postDTO) {
        boolean updated = postService.updatePost(postId, postDTO);
        if (updated) {
            return ResponseEntity.ok("Post updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
    }

    @DeleteMapping("/public/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable UUID postId) {
        boolean deleted = postService.deletePost(postId);
        if (deleted) {
            return ResponseEntity.ok("Post deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
    }
}
