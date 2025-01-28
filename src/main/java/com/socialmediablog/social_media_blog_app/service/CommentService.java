package com.socialmediablog.social_media_blog_app.service;

import com.socialmediablog.social_media_blog_app.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getAllCommentsByPostIs(long postId);

    CommentDto getCommentByPostIdAndCommentId(long postId, long commentId);

    CommentDto createCommentForPostId(long postId, CommentDto commentDto);

    //update
    CommentDto updateCommentByPostIdAndCommentId(long postId, long commentId, CommentDto commentDto);

    //Delete particular comment
    String deleteCommentByPostIdAndCommentId(long postId, long commentId);

    //Delete all comments
String deleteAllCommentsOfPostsFromPostId(long postId);
}
