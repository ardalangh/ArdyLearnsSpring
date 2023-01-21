package com.ardyspringpractice.restfulwebservices.jpa;

import com.ardyspringpractice.restfulwebservices.user.Post;
import com.ardyspringpractice.restfulwebservices.user.User;
import com.ardyspringpractice.restfulwebservices.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {


    private UserJpaRepository userRepository;
    private PostJpaRepository postRepository;

    public UserJpaResource(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping(path = "jpa/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "jpa/users/{userId}")
    public EntityModel<User> getUserById(@PathVariable int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new UserNotFoundException("id: " + userId);
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }


    @DeleteMapping(path = "jpa/users/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userRepository.deleteById(userId);
    }

    @GetMapping(path = "jpa/users/{userId}/posts")
    public List<Post> getPostsForUser(@PathVariable int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new UserNotFoundException("id: " + userId);
        return user.get().getPosts();
    }

    @PostMapping(path = "jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }



    @PostMapping(path = "jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("id: " + id);

        post.setUser(user.get());
        Post savedPost = this.postRepository.save(post);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


}
