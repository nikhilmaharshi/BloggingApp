package com.nikhil.blog.services;

import com.nikhil.blog.dto.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment(Integer commentId);
}
