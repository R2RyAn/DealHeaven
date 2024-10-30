package com.dealheaven.controllers;

import com.dealheaven.models.Post;
import com.dealheaven.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody Post post) throws ExecutionException, InterruptedException {
        postService.addPost(post);
        return ResponseEntity.ok("Post created successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) throws ExecutionException, InterruptedException {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable String id) throws ExecutionException, InterruptedException {
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() throws ExecutionException, InterruptedException {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/all/{email}")
    public ResponseEntity<List<Post>> getAllPostsFromUser(@PathVariable String email) throws ExecutionException, InterruptedException {
        List<Post> posts = postService.getAllPostsFromUser(email);
        return ResponseEntity.ok(posts);
    }




}
