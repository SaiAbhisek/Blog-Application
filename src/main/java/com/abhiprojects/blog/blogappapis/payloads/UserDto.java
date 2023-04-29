package com.abhiprojects.blog.blogappapis.payloads;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {

    private int id;

    @NotEmpty  //Validation annotations
    @Size(min=4,message = "Username must be minimum of 4 characters")
    private String name;

    @Email(message = "Email address is not valid!!")
    private String email;

    @NotEmpty
    @Size(min=3,max = 10,message ="password must be min of 3 characters and max of 10 characters")
    private String password;

    @NotEmpty
    private String about;

}
