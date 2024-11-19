package com.nikhil.blog.services;

import com.nikhil.blog.dto.PostDto;
import com.nikhil.blog.utils.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto request,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto request,Integer postId);
    void deletePost(Integer postId);
    PostDto getPostById(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy);
    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> getPostsByUser(Integer userId);
    List<PostDto> searchPosts(String keyword);

}
