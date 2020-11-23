package springangularjwt.auth.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springangularjwt.auth.data.model.User;
import springangularjwt.auth.service.model.UserServiceModel;
import springangularjwt.auth.service.service.UserService;
import springangularjwt.auth.web.model.JwtToken;
import springangularjwt.auth.web.model.MessageResponseModel;
import springangularjwt.auth.web.model.UserLoginServiceModel;
import springangularjwt.auth.web.model.UserRegisterServiceModel;
import springangularjwt.auth.web.security.JwtUtils;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(
            UserService userService,
            ModelMapper modelMapper,
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils) {

        this.userService = userService;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> userRegister(@RequestBody UserRegisterServiceModel userRegisterServiceModel) {

        if (this.userService.userExistsByEmail(userRegisterServiceModel.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponseModel("Email is already taken!"));
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterServiceModel, UserServiceModel.class);

        try {
            this.userService.register(userServiceModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponseModel("There is a problem bla bla"));
        }

        return ResponseEntity.ok().body(new MessageResponseModel("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginServiceModel userLoginServiceModel) {

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginServiceModel.getEmail(),
                        userLoginServiceModel.getPassword()));

        if (!authentication.isAuthenticated()) {
            return ResponseEntity.badRequest().body(new MessageResponseModel("Wrong email or password!"));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = this.jwtUtils.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtToken(jwtToken, user.getId(), user.getEmail(), roles));
    }
}




















