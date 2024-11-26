package com.socialmediablog.social_media_blog_app.dto;

import lombok.Data;

@Data
public class PostDto {
    private Long Id;
    private String title;
    private String description;
    private String content;
}
