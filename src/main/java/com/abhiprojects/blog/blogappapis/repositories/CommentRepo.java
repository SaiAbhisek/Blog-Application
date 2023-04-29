package com.abhiprojects.blog.blogappapis.repositories;

import com.abhiprojects.blog.blogappapis.entities.Comment;
import com.abhiprojects.blog.blogappapis.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

    List<Comment> findByPost(Post post);
}
