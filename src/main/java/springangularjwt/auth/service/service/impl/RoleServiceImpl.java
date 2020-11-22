package springangularjwt.auth.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springangularjwt.auth.data.model.Role;
import springangularjwt.auth.data.repository.RoleRepository;
import springangularjwt.auth.service.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private static final String ROLE_USER = "ROLE_USER";

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getUserRole() {
        return roleRepository.getRoleByName(ROLE_USER);
    }

    @Override
    public void createRole(Role role) {
        this.roleRepository.saveAndFlush(role);
    }

    @Override
    public List<Role> getAll() {
        return this.roleRepository.findAll();
    }
}
