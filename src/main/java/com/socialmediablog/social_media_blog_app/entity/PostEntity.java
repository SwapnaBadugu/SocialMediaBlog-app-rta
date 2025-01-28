package com.socialmediablog.social_media_blog_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")

@Data
@NoArgsConstructor // this annotation is required by the spring framework
@AllArgsConstructor // this annotation is required by the spring framework
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    //OneToManyRelationship
    //Single posts can have multiple comments
    @OneToMany(mappedBy = "postEntity")
    private Set<CommentEntity> comments = new HashSet<>();

}
