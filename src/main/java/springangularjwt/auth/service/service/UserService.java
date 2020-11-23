package springangularjwt.auth.service.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springangularjwt.auth.data.model.User;
import springangularjwt.auth.service.model.UserServiceModel;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUserByEmail(String email);

    boolean userExistsByEmail(String email);

    void register(UserServiceModel userServiceModel);

    List<User> getAllUser();

    User getUserById(String userId);
}
