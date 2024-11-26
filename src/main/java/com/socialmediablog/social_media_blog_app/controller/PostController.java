package com.socialmediablog.social_media_blog_app.controller;

import com.socialmediablog.social_media_blog_app.dto.PostDto;
import com.socialmediablog.social_media_blog_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller has no Body mapping
@RestController
// Rest Controller has the response body as well and also used in mvc full stack return to different pages
@RequestMapping("/v1/api/posts") //this is the main/base entry point (http server will hit here first )
public class PostController {

    @Autowired
    private PostService postService;


    //get all posts
    //v1/api/posts
    @GetMapping
    public List<PostDto> fetchAllPost() {
        return this.postService.getAllPosts();
    }

    //get post by id
    //v1/api/posts{postId}
    @GetMapping("/{postId}")
    public PostDto fetchPostById(long postId) {
        return this.postService.getPostById(postId);
    }

    //create post
    //v1/api/post
    @PostMapping
    public PostDto savePost(@RequestBody PostDto postDto) {
        return this.postService.createPost(postDto);
    }

    //update post
    //v1/api/posts{postId}
    @PutMapping("/{postId}")
    public PostDto updatePost(@RequestBody PostDto postIdToBeUpdated, @PathVariable long postId) {
        return this.postService.updatePost(postIdToBeUpdated, postId);
    }

    //delete post
    //v1/api/posts{postId}
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable long postId) {
        boolean isDeleted = this.postService.deletePostById(postId);
        if (isDeleted) {
            return ResponseEntity.ok("Post " + postId + " delete successful");
        } else {
            return new ResponseEntity<>("Error while deleting post " + postId, HttpStatus.NOT_FOUND);
        }
    }
}
