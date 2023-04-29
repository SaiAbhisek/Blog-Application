package com.abhiprojects.blog.blogappapis.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
    private int id;
    private String Content;

}
