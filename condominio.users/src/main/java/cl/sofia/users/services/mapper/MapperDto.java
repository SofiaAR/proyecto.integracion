package cl.sofia.users.services.mapper;

import cl.sofia.users.model.dtos.RoleDto;
import cl.sofia.users.model.dtos.UserDto;
import cl.sofia.users.model.entities.Role;
import cl.sofia.users.model.entities.User;
import java.util.stream.Collectors;

public class MapperDto {

    public static UserDto userEntityToUserDto(User user){
        if (user== null)
            return null;
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setCreated_at(user.getCreated_at());
        userDto.setStatus(user.getStatus());
        userDto.setDni(user.getDni());
        userDto.setRoles(user.getRoles().stream().map(MapperDto::roleEntityToRoleDto).collect(Collectors.toSet())); // EXPLICAR DAVID
        return userDto;
    }

    public static RoleDto roleEntityToRoleDto(Role role){
        if (role == null)
            return null;
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }
}
