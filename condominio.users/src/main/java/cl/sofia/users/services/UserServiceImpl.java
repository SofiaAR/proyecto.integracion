package cl.sofia.users.services;

import cl.sofia.users.model.entities.User;
import cl.sofia.users.model.repositories.RoleRepository;
import cl.sofia.users.model.repositories.UserRepository;
import cl.sofia.users.model.dtos.UserDto;
import cl.sofia.users.services.mapper.MapperDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void save(UserDto userDto) {
        User user = new User();
        user.setDni(userDto.getDni());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setStatus("ACTIVO");
        user.setCreated_at(LocalDateTime.now());
        if (!userDto.getRoles().isEmpty()) {
            userDto.getRoles().forEach(roleDto -> user.getRoles().add(roleRepository.getById(roleDto.getId())));
        }
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(userRepository.getById(id));
    }

    @Override
    public UserDto findByDni(String dni) {
        User userEntity = userRepository.findByDni(dni);
        return MapperDto.userEntityToUserDto(userEntity);
    }

    @Override
    public UserDto findById(Long userId) {
        User userEntity = userRepository.findById(userId).orElseThrow();
        return MapperDto.userEntityToUserDto(userEntity);
    }

    @Override
    public UserDto findByName(String username) {
        User userEntity = userRepository.findByName(username);
        return MapperDto.userEntityToUserDto(userEntity);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> users = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            users.add(MapperDto.userEntityToUserDto(user));
        }
        return users;
    }

    @Override
    public void update(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow();
        user.setDni(userDto.getDni());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setLastName(userDto.getLastName());
        userRepository.save(user);
    }
}
