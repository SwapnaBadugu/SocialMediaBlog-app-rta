package com.socialmediablog.social_media_blog_app.controller;

import com.socialmediablog.social_media_blog_app.dto.CommentDto;
import com.socialmediablog.social_media_blog_app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //Create Comment
    // /posts/{/post-id}/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto) {
        CommentDto savedCommentDto = this.commentService.createCommentForPostId(postId, commentDto);
        return new ResponseEntity<>(savedCommentDto, HttpStatus.CREATED);
    }
    //Get all Comments for Post REST API - /posts/{postId}/comments

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> fetchAllCommentsByPostId(@PathVariable long postId) {
        List<CommentDto> commentDtoList = this.commentService.getAllCommentsByPostIs(postId);
        return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
    }
    //Get Single Comment REST API - /posts/{postId}/comments/{commentId}

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> fetchCommentByPostIdAndCommentId(@PathVariable long postId, @PathVariable long commentId) {
        CommentDto commentDto = this.commentService.getCommentByPostIdAndCommentId(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    // Update Mapping
    // Update Comment Rest API  - /posts/{postId}/comments/{commentID}
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentByPostIdAndCommentId(@PathVariable long postId,@PathVariable long commentId, @RequestBody CommentDto commentDto) {
        CommentDto updatedCommentDto = this.commentService.updateCommentByPostIdAndCommentId(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedCommentDto, HttpStatus.OK);
    }

    // Delete Mapping
    // Delete Comment Rest API  - /posts/{postId}/comments/{commentID}
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentByPostIdAndCommentId(@PathVariable long postId,@PathVariable long commentId) {
        String message = this.commentService.deleteCommentByPostIdAndCommentId(postId, commentId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // Delete Mapping
    // Delete All Comments Rest API  - /posts/{postId}/comments}
    @DeleteMapping("/posts/{postId}/comments")
    public ResponseEntity<String> deleteAllCommentByPostId(@PathVariable long postId) {
        String message = this.commentService.deleteAllCommentsOfPostsFromPostId(postId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
