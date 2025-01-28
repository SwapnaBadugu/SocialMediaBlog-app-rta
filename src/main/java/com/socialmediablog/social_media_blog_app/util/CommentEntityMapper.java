package com.socialmediablog.social_media_blog_app.util;

import com.socialmediablog.social_media_blog_app.dto.CommentDto;
import com.socialmediablog.social_media_blog_app.entity.CommentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentEntityMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CommentDto convertEntitytoDto(CommentEntity commentEntity) {
        if (commentEntity != null) {
//            CommentDto commentDto = new CommentDto();

//            commentDto.setId(commentEntity.getId());
//            commentDto.setUserName(commentEntity.getUserName());
//            commentDto.setEmail(commentEntity.getEmail());
//            commentDto.setBody(commentEntity.getBody());
//            return commentDto;
            return this.modelMapper.map(commentEntity, CommentDto.class);
        }
        return null;
    }

    public CommentEntity convertDtotoEntity(CommentDto commentDto) {
        if (commentDto != null) {
//            CommentEntity commentEntity = new CommentEntity();
//
//            commentEntity.setUserName(commentDto.getUserName());
//            commentEntity.setEmail(commentDto.getEmail());
//            commentEntity.setBody(commentDto.getBody());
//            return commentEntity;
            return this.modelMapper.map(commentDto, CommentEntity.class);
        }
        return null;
    }
}
