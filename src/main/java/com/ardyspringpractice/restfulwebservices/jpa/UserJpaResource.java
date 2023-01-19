package com.ardyspringpractice.restfulwebservices.jpa;

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


    private UserJpaRepository repository;

    public UserJpaResource(UserJpaRepository repository) {
        this.repository = repository;
    }


    @GetMapping(path = "jpa/users")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping(path = "jpa/users/{userId}")
    public EntityModel<User> getUserById(@PathVariable int userId) {
        Optional<User> user = repository.findById(userId);
        if (user.isEmpty())
            throw new UserNotFoundException("id: " + userId);
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }


    @DeleteMapping(path = "jpa/users/{userId}")
    public void deleteUser(@PathVariable int userId) {
        repository.deleteById(userId);
    }

    @PostMapping(path = "jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


}
