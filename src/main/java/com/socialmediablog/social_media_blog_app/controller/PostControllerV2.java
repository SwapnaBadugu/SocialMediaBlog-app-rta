package com.socialmediablog.social_media_blog_app.controller;

import com.socialmediablog.social_media_blog_app.dto.PostDto;
import com.socialmediablog.social_media_blog_app.payload.PostResponse;
import com.socialmediablog.social_media_blog_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v2/api/posts")
public class PostControllerV2 {
    @Autowired
    private PostService postService;


    //get all posts
    //v2/api/posts
    @GetMapping
    public PostResponse fetchAllPost(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                     @RequestParam(value = "pageSize", defaultValue = "0", required = false) int pageSize,
                                     @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                     @RequestParam(value = "sortDirection", defaultValue = "ASC", required = false) String sortDirection) {
        return this.postService.getAllPosts(pageNo, pageSize, sortBy, sortDirection);
    }
}
