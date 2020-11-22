package springangularjwt.auth.service.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springangularjwt.auth.data.model.User;
import springangularjwt.auth.service.model.UserServiceModel;

public interface UserService extends UserDetailsService {

    User getUserByEmail(String email);

    boolean userExistsByEmail(String email);

    void register(UserServiceModel userServiceModel);
}
