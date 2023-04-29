package com.abhiprojects.blog.blogappapis.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryDto {

    private Integer categoryId;

    @NotBlank
    @Size(min = 4,message = "Min size of category title must be 4")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10,message = "Category description size must contain more min of 10 characters")
    private String categoryDescription;
}
