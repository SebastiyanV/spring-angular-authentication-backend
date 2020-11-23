package springangularjwt.auth.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springangularjwt.auth.data.model.User;
import springangularjwt.auth.service.service.UserService;
import springangularjwt.auth.web.model.MessageResponseModel;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<User> allUsers() {
        return this.userService.getAllUser();
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public User profile(Principal principal) {
        return this.userService.getUserByEmail(principal.getName());
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        User user = this.userService.getUserById(userId);
        if (null == user) {
            return ResponseEntity.badRequest().body(new MessageResponseModel("User not found!"));
        }
        return ResponseEntity.ok().body(user);
    }

}
