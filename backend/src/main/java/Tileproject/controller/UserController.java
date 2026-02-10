package Tileproject.controller;

import Tileproject.model.User;
import Tileproject.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // returns logged in user (from JWT)
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/me")
    public User me() {
        return userService.getOrCreateUserFromJWT();


    }
}
