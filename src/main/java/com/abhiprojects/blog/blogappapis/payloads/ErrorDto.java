package com.abhiprojects.blog.blogappapis.payloads;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDto {
    private String message;
}
