package com.abhiprojects.blog.blogappapis.repositories;

import com.abhiprojects.blog.blogappapis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {


}
