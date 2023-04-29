package com.abhiprojects.blog.blogappapis.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Comments")
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
