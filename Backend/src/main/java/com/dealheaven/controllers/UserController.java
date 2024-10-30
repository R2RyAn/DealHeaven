package com.dealheaven.controllers;

import com.dealheaven.models.User;
import com.dealheaven.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        userService.createUser(user);
        return ResponseEntity.ok("User created successfully with id: " + user.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) throws ExecutionException, InterruptedException {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) throws ExecutionException, InterruptedException {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }



    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        userService.updateUser(user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) throws ExecutionException, InterruptedException {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
