package com.abhiprojects.blog.blogappapis.services;

import com.abhiprojects.blog.blogappapis.entities.Post;
import com.abhiprojects.blog.blogappapis.payloads.PostDto;
import com.abhiprojects.blog.blogappapis.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPostsByTitle(String keyword);

}
