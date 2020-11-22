package springangularjwt.auth.service.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springangularjwt.auth.data.model.User;
import springangularjwt.auth.data.repository.UserRepository;
import springangularjwt.auth.service.model.UserServiceModel;
import springangularjwt.auth.service.service.RoleService;
import springangularjwt.auth.service.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            ModelMapper modelMapper,
            RoleService roleService,
            BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findUserByEmail(email)
                .orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return this.userRepository.findUserByEmail(email).isPresent();
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setRoles(List.of(this.roleService.getUserRole()));
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepository.saveAndFlush(user);
    }
}
