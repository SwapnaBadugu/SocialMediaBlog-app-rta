package com.socialmediablog.social_media_blog_app.repository;

import com.socialmediablog.social_media_blog_app.entity.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query(value = "SELECT * FROM Comments where post_id =?1", nativeQuery = true)
    List<CommentEntity> findByPostId(long postId);

    @Modifying
    @Transactional
    @Query(value = "Delete From Comments Where post_id = ?1", nativeQuery = true)
    void deleteByPostId(long postId);

}
