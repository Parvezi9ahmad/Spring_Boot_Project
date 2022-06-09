package com.springboot.blog.service;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId,CommentDto commentDto);

    List<CommentDto> getCommentByPostId(Long PostId);

    CommentDto getCommentById(long postId,Long commentId);

    CommentDto updatecomment(Long postId,Long commentId,CommentDto commentDto);

    void deleteComment(long postId,Long commentId);
}
