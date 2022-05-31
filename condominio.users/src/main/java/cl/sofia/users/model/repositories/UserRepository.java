package cl.sofia.users.model.repositories;

import cl.sofia.users.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User where name=:username")
    User findByName(String username);

    User findByDni(String dni);

}