package com.socialmediablog.social_media_blog_app.service.impl;

import com.socialmediablog.social_media_blog_app.dto.CommentDto;
import com.socialmediablog.social_media_blog_app.entity.CommentEntity;
import com.socialmediablog.social_media_blog_app.entity.PostEntity;
import com.socialmediablog.social_media_blog_app.exception.ResourceNotFoundException;
import com.socialmediablog.social_media_blog_app.repository.CommentRepository;
import com.socialmediablog.social_media_blog_app.repository.PostRepository;
import com.socialmediablog.social_media_blog_app.service.CommentService;
import com.socialmediablog.social_media_blog_app.util.CommentEntityMapper;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentEntityMapper commentEntityMapper;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<CommentDto> getAllCommentsByPostIs(long postId) {
        List<CommentEntity> commentEntityList = this.commentRepository.findByPostId(postId);
        if (commentEntityList != null && !commentEntityList.isEmpty()) {
            return commentEntityList.stream().map(commentEntity -> this.commentEntityMapper.convertEntitytoDto(commentEntity)).toList();
        }
        return null;
    }

    @Override
    public CommentDto getCommentByPostIdAndCommentId(long postId, long commentId) {
        //validate or fetch post entity details from post table of DB

        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post id not found :: " + postId));
        // fetch comment by comment id
        CommentEntity commentEntity = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment id not found :: " + commentId));
        //Validate the particular comment belong to the post

        if (commentEntity != null && postEntity != null) {
            if (!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
                throw new RuntimeException("Bad Request :: Comment Not Found");
            } else {
                return this.commentEntityMapper.convertEntitytoDto(commentEntity);
            }
        }
        throw new RuntimeException("Bad Request");
    }

    @Override
    public CommentDto createCommentForPostId(long postId, CommentDto commentDto) {

        //Convert CommentDto to CommentEntity
        CommentEntity commentEntity = this.commentEntityMapper.convertDtotoEntity(commentDto);

        //Fetch Post Entity from Post Table using postId
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ID NotFound:: " + postId));

        //Attach or Set Comment to Particular or associated Post Entity
        commentEntity.setPostEntity(postEntity);

        //Save Comment Entity to DB
        CommentEntity newlySavedCommentEntity = this.commentRepository.save(commentEntity);

        //Map Comment Entity to Comment DTO and return newly create Comment DTO
        return this.commentEntityMapper.convertEntitytoDto(newlySavedCommentEntity);
    }

    @Override
    public CommentDto updateCommentByPostIdAndCommentId(long postId, long commentId, CommentDto commentDto) {

        // Fetch Post Entity using Post Repository from Post id
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post Id Not Found:: " + postId));

        //Fetch Comment Entity using Comment Repository from Comment id
        CommentEntity commentEntity = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment Id Not Found:: " + commentId));

        // Validate Comment belongs to the particular Post
        if (commentEntity != null && postEntity != null) {
            if (!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
                throw new RuntimeException("Bad Request:: Comment Not Found");
            }
        }

        // if valid then update old comment details with the new Comment Dto
        if (commentEntity != null && commentDto != null) {
            commentEntity.setEmail(commentDto.getEmail());
            commentEntity.setUserName(commentDto.getUserName());
            commentEntity.setBody(commentDto.getBody());
        }

        //Save updated Comment Entity to DB
        CommentEntity newlySavedCommentEntity = this.commentRepository.save(commentEntity);

        return this.commentEntityMapper.convertEntitytoDto(newlySavedCommentEntity);
    }

    @Override
    public String deleteCommentByPostIdAndCommentId(long postId, long commentId) {

        // Fetch Post Entity using Post Repository from Post id
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post Id Not Found:: " + postId));

        //Fetch Comment Entity using Comment Repository from Comment id
        CommentEntity commentEntity = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment Id Not Found:: " + commentId));

        // Validate Comment belongs to the particular Post
        if (commentEntity != null && postEntity != null) {
            if (!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
                throw new RuntimeException("Bad Request:: Comment Not Found");
            }
        }
        this.commentRepository.delete(commentEntity);
        return "Successfully Deleted Comment:: " + commentId;
    }

    @Override
    public String deleteAllCommentsOfPostsFromPostId(long postId) {
        this.commentRepository.deleteByPostId(postId);
        return "Successfully Deleted All Comments for Post Id:: " + postId;
    }
}
