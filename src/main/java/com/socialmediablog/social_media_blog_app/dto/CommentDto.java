package com.socialmediablog.social_media_blog_app.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CommentDto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String userName;
    private String email;
    private String body;

    //ManyToOne Relationship
}
