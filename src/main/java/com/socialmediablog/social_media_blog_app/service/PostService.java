package com.socialmediablog.social_media_blog_app.service;

import com.socialmediablog.social_media_blog_app.dto.PostDto;
import com.socialmediablog.social_media_blog_app.payload.PostResponse;

import java.util.List;

public interface PostService {

    // Get all the posts
    List<PostDto> getAllPosts();

    PostResponse getAllPosts(int pageNo, int pageSize);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDirection);

    //Get Post by Id
    PostDto getPostById(long id);

    //Create Post
    PostDto createPost(PostDto postDtoToBeCreated);

    //Update Post
    PostDto updatePost(PostDto postDto, long postIdToBeUpdated);

    //Delete Post
    boolean deletePostById(long PostIdToBeDeleted);

}
