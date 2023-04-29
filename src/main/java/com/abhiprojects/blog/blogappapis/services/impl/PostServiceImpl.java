package com.abhiprojects.blog.blogappapis.services.impl;

import com.abhiprojects.blog.blogappapis.entities.Category;
import com.abhiprojects.blog.blogappapis.entities.Post;
import com.abhiprojects.blog.blogappapis.entities.User;
import com.abhiprojects.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.abhiprojects.blog.blogappapis.payloads.PostDto;
import com.abhiprojects.blog.blogappapis.payloads.PostResponse;
import com.abhiprojects.blog.blogappapis.repositories.CategoryRepo;
import com.abhiprojects.blog.blogappapis.repositories.PostRepo;
import com.abhiprojects.blog.blogappapis.repositories.UserRepo;
import com.abhiprojects.blog.blogappapis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;



    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found"));

        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category Not Found"));

        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost=this.postRepo.save(post);

        return this.modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not Found"));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post savedPost=this.postRepo.save(post);
        PostDto postDto1=this.modelMapper.map(savedPost, PostDto.class);
        return postDto1;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not Found"));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection) {

        Sort sort=null;
        if(sortDirection.equalsIgnoreCase("asc"))
        {
            sort=Sort.by(sortBy).ascending();
        }
        else{
            sort=Sort.by(sortBy).descending();
        }

        Pageable p= PageRequest.of(pageNumber,pageSize,sort);

        Page<Post> pagePosts =this.postRepo.findAll(p);
        List<Post> allPosts=pagePosts.getContent();

        List<PostDto> postDtos=allPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastpage(pagePosts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not Found"));
        PostDto postDto=this.modelMapper.map(post,PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category Not Found"));

        List<Post> posts=this.postRepo.findByCategory(cat);
        List<PostDto> postDtos=posts.stream().map((post -> this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        List<Post> posts=this.postRepo.findByUser(user);
        List<PostDto> postDtos=posts.stream().map((post -> this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPostsByTitle(String keyword) {
        List<Post> posts=this.postRepo.findByTitleContaining(keyword);
       List<PostDto> postDtos= posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }


}
