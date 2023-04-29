package com.abhiprojects.blog.blogappapis.services.impl;

import com.abhiprojects.blog.blogappapis.entities.Comment;
import com.abhiprojects.blog.blogappapis.entities.Post;
import com.abhiprojects.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.abhiprojects.blog.blogappapis.payloads.CommentDto;
import com.abhiprojects.blog.blogappapis.payloads.PostDto;
import com.abhiprojects.blog.blogappapis.repositories.CommentRepo;
import com.abhiprojects.blog.blogappapis.repositories.PostRepo;
import com.abhiprojects.blog.blogappapis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepo commentRepo;


    @Override
    public CommentDto createComment(Integer postId, CommentDto commentDto) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not Found"));

       Comment comment=this.modelMapper.map(commentDto, Comment.class);

       comment.setPost(post);

        post.getComments().add(comment);
        this.postRepo.save(post);
       Comment savedComment= this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public List<CommentDto> getComments() {

        List<Comment> comments=this.commentRepo.findAll();
        List<CommentDto> commentDtos=comments.stream().map(comment1 -> this.modelMapper.map(comment1,CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment Not Found"));
        this.commentRepo.delete(comment);
    }

    @Override
    public List<CommentDto> getCommentByPostId(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not Found"));
        List<Comment> comments=this.commentRepo.findByPost(post);
        List<CommentDto> commentDtos=comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
        return commentDtos;
    }


}
