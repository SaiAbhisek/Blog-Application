package com.abhiprojects.blog.blogappapis.repositories;

import com.abhiprojects.blog.blogappapis.entities.Category;
import com.abhiprojects.blog.blogappapis.entities.Post;
import com.abhiprojects.blog.blogappapis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//implementation class will be automatically created by spring boot at run time
public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);

}
