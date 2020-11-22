package springangularjwt.auth.service.service;

import springangularjwt.auth.data.model.Role;

import java.util.List;

public interface RoleService {

    Role getUserRole();

    void createRole(Role role);

    List<Role> getAll();
}
