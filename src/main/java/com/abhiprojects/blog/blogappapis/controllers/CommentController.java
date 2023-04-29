package com.abhiprojects.blog.blogappapis.controllers;

import com.abhiprojects.blog.blogappapis.entities.Comment;
import com.abhiprojects.blog.blogappapis.entities.Post;
import com.abhiprojects.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.abhiprojects.blog.blogappapis.payloads.CommentDto;
import com.abhiprojects.blog.blogappapis.payloads.PostResponse;
import com.abhiprojects.blog.blogappapis.repositories.PostRepo;
import com.abhiprojects.blog.blogappapis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostRepo postRepo;

    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto commentDto1=this.commentService.createComment(postId, commentDto);

        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CommentDto>> getComments()
    {
        List<CommentDto> commentDtos=this.commentService.getComments();
        return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer commentId)
    {
        this.commentService.deleteComment(commentId);
        return ResponseEntity.ok(Map.of("message","Comment deleted Successfully"));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable Integer postId)
    {
        List<CommentDto> commentDtos=this.commentService.getCommentByPostId(postId);
        return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }

}
