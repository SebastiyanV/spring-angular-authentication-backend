package springangularjwt.auth.configuration.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springangularjwt.auth.data.enumerate.RoleEnum;
import springangularjwt.auth.data.model.Role;
import springangularjwt.auth.service.service.RoleService;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class RoleInitialData {

    private final RoleService roleService;

    @Autowired
    public RoleInitialData(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        if (roleService.getAll().size() == 0) {
            Arrays.stream(RoleEnum.values())
                    .forEach(role -> {
                        this.roleService.createRole(new Role(role.toString()));
                    });
        }
    }
}
