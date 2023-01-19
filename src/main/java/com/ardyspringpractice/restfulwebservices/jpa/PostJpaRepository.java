package com.ardyspringpractice.restfulwebservices.jpa;

import com.ardyspringpractice.restfulwebservices.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Integer> {

}
