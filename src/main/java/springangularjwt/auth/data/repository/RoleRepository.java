package springangularjwt.auth.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springangularjwt.auth.data.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role getRoleByName(String name);
}
