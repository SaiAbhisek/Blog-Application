package com.abhiprojects.blog.blogappapis.repositories;

import com.abhiprojects.blog.blogappapis.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
