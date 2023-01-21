package com.ardyspringpractice.restfulwebservices.jpa;

import com.ardyspringpractice.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

}
