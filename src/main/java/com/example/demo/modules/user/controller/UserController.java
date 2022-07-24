package com.example.demo.modules.user.controller;

import com.example.demo.modules.user.entities.UserEntity;
import com.example.demo.modules.user.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "User management")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserEntity>> getAll() {
        List<UserEntity> users = this.userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable String id) {
        UserEntity user = this.userService.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping()
    public ResponseEntity<UserEntity> create(@RequestBody @Valid UserEntity user) {
        UserEntity createdUser = this.userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PatchMapping()
    public ResponseEntity<UserEntity> update(@RequestBody @Valid UserEntity user) {
        UserEntity updatedUser = this.userService.update(user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.userService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
