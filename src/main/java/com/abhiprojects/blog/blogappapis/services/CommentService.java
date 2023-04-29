package com.abhiprojects.blog.blogappapis.services;

import com.abhiprojects.blog.blogappapis.payloads.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Integer postId,CommentDto commentDto);

    List<CommentDto> getComments();

    void deleteComment(Integer commentId);

    List<CommentDto> getCommentByPostId(Integer postId);
}
