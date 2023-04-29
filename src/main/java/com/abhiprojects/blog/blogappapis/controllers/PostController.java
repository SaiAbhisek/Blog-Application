package com.abhiprojects.blog.blogappapis.controllers;

import com.abhiprojects.blog.blogappapis.config.AppConstants;
import com.abhiprojects.blog.blogappapis.payloads.PostDto;
import com.abhiprojects.blog.blogappapis.payloads.PostResponse;
import com.abhiprojects.blog.blogappapis.services.FileService;
import com.abhiprojects.blog.blogappapis.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    //create posts........
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId
                                                ){
        PostDto createdPost=this.postService.createPost(postDto, userId,categoryId);
        return  new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    //get posts by user...........
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
    {
        List<PostDto> postDtos=this.postService.getPostsByUser(userId);
        return  new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    //get posts by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
    {
        List<PostDto> postDtos=this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    //get post by postId
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
    {
        PostDto postDto=this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDirection",defaultValue = AppConstants.SORT_DIR,required = false) String sortDirection
            )
    {
        PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDirection);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return ResponseEntity.ok(Map.of("message","Deleted successfully"));
    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
    {
        PostDto postDto1=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    //search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable String keywords){
        List<PostDto> postDtos=this.postService.searchPostsByTitle(keywords);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }


    //method to upload file
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {

        PostDto postDto=this.postService.getPostById(postId);
        String fileName=this.fileService.uploadImage(path,image);

        postDto.setImageName(fileName);
        PostDto updatedPostDto=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPostDto,HttpStatus.OK);
    }

    //method to serve the files
    @GetMapping(value = "post/image/{imageName}")
    public void getResource(@PathVariable String imageName,
                            HttpServletResponse response) throws IOException {
        InputStream resource=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
