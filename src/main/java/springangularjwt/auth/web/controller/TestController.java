package springangularjwt.auth.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springangularjwt.auth.data.model.User;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public User allAccess(Principal principal) {
        System.out.println(principal.getName());
        //        User user2 = new User();
//        user2.setId(UUID.randomUUID().toString());
//        user2.setEmail("subi@abv.bg");
//        user2.setFirstName("subi");
//        user2.setLastName("vuchkov");
//        user2.setRoles(List.of(new Role("USER_ROLE")));
//        user2.setPassword("parola");
//        return user2;
        return null;
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
