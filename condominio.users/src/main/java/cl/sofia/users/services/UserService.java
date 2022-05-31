package cl.sofia.users.services;

import cl.sofia.users.model.dtos.UserDto;
import java.util.List;


public interface UserService {

    void save(UserDto userDto);

    void delete(Long id);

    UserDto findByDni(String dni);

    UserDto findById(Long userId);

    UserDto findByName(String username);

    List<UserDto> findAll();

    void update(UserDto userDto);
}
