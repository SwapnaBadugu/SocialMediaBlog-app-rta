package com.socialmediablog.social_media_blog_app.util;

import com.socialmediablog.social_media_blog_app.dto.CommentDto;
import com.socialmediablog.social_media_blog_app.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityMapper {


    public CommentDto convertEntitytoDto(CommentEntity commentEntity) {
        if (commentEntity != null) {
            CommentDto commentDto = new CommentDto();

            commentDto.setId(commentEntity.getId());
            commentDto.setUserName(commentEntity.getUserName());
            commentDto.setEmail(commentEntity.getEmail());
            commentDto.setBody(commentEntity.getBody());
            return commentDto;
        }
        return null;
    }

    public CommentEntity convertDtotoEntity(CommentDto commentDto) {
        if (commentDto != null) {
            CommentEntity commentEntity = new CommentEntity();

            commentEntity.setUserName(commentDto.getUserName());
            commentEntity.setEmail(commentDto.getEmail());
            commentEntity.setBody(commentDto.getBody());
            return commentEntity;
        }
        return null;
    }
}
