package com.nbkarthi.auth_jwt.controller;


import com.nbkarthi.auth_jwt.auth.RegisterRequest;
import com.nbkarthi.auth_jwt.auth.UpdateRequest;
import com.nbkarthi.auth_jwt.model.User;
import com.nbkarthi.auth_jwt.model.UserDTO;
import com.nbkarthi.auth_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/user-controller")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> sayHello() {
        Map<String, String> objectMap = new HashMap<>();
        objectMap.put("data", "API_TEST_OK");
        return ResponseEntity.ok(objectMap);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/update")
    public User updateUser(@RequestBody UpdateRequest request) {
        return userService.updateUser(request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestBody UpdateRequest request) {
        return userService.deleteUser(request);
    }

//    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/users")
    public Optional<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }
}
