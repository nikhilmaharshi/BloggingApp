package com.nikhil.blog.services;

import com.nikhil.blog.dto.PostDto;
import com.nikhil.blog.entities.Category;
import com.nikhil.blog.entities.Post;
import com.nikhil.blog.entities.User;
import com.nikhil.blog.exceptions.ResourceNotFoundException;
import com.nikhil.blog.repositories.CategoryRepo;
import com.nikhil.blog.repositories.PostRepo;
import com.nikhil.blog.repositories.UserRepo;
import com.nikhil.blog.utils.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto request,Integer userId,Integer categoryId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        Post post = dtoToPost(request);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        return postToDto(postRepo.save(post));
    }

    @Override
    public PostDto updatePost(PostDto request, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setImageName(request.getImageName());

        return postToDto(postRepo.save(post));
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));

        return postToDto(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Post> postPage = postRepo.findAll(pageable);
        List<Post> posts = postPage.getContent();

        List<PostDto> postDtos = posts.stream().map(this::postToDto).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElement(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        List<PostDto> posts = postRepo.findByCategory(category).stream().map(this::postToDto).toList();

        return posts;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
        List<PostDto> posts = postRepo.findByUser(user).stream().map(this::postToDto).toList();

        return posts;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map(this::postToDto).toList();
        return postDtos;
    }

    private Post dtoToPost(PostDto dto) {
        return modelMapper.map(dto, Post.class);

    }

    private PostDto postToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }
}
