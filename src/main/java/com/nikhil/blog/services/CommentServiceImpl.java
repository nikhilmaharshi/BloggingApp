package com.nikhil.blog.services;

import com.nikhil.blog.dto.CommentDto;
import com.nikhil.blog.entities.Comment;
import com.nikhil.blog.entities.Post;
import com.nikhil.blog.exceptions.ResourceNotFoundException;
import com.nikhil.blog.repositories.CommentRepo;
import com.nikhil.blog.repositories.PostRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        Comment comment = dtoToComment(commentDto);
        comment.setPost(post);
        return commentToDto(commentRepo.save(comment));
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment Id", commentId));
        commentRepo.delete(comment);
    }

    private Comment dtoToComment(CommentDto dto){
        return modelMapper.map(dto,Comment.class);
    }
    private CommentDto commentToDto(Comment comment){
        return modelMapper.map(comment,CommentDto.class);
    }
}
