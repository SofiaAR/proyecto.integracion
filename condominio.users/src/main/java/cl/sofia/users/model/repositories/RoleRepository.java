package cl.sofia.users.model.repositories;

import cl.sofia.users.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
