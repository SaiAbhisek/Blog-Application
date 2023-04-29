package com.abhiprojects.blog.blogappapis.payloads;


import com.abhiprojects.blog.blogappapis.entities.Category;
import com.abhiprojects.blog.blogappapis.entities.Comment;
import com.abhiprojects.blog.blogappapis.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class PostDto {

    private int postId;
    private String title;
    private String content;

    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
    private List<CommentDto> comments=new ArrayList<>();

}
