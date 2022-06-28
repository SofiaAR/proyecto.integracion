package cl.sofia.users.services;

import cl.sofia.users.model.dtos.UserDto;
import cl.sofia.users.model.entities.User;
import cl.sofia.users.model.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserTestServiceImplTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByDni() {
        //when
        when(userRepository.findByDni(any())).thenReturn(getUser());

        //given
        UserDto userResult = userService.findByDni("123456789-1");

        //then
        assertNotNull(userResult);
        assertEquals("Sofia", userResult.getName());
        assertEquals("ACTIVO", userResult.getStatus());
        assertEquals("123456789-1", userResult.getDni());
    }

    @Test
    void findById() {


        //when
        when(userRepository.findById(1L)).thenReturn(Optional.of(getUser()));

        //given
        UserDto userFind = userService.findById(1L);

        //then
        assertNotNull(userFind);
    }

    @Test
    void findByName() {

        //when
        when(userRepository.findByName("Sofia")).thenReturn(getUser());

        //given
        UserDto userFind = userService.findByName("Sofia");

        //then
        assertNotNull(userFind);
    }

    @Test
    void findAll() {

        when(userRepository.findAll()).thenReturn(List.of(getUser()));

        List<UserDto> userDtoList = userService.findAll();

        assertFalse(userDtoList.isEmpty());
    }

    @Test
    void update() {

        User user = new User();
        user.setName("Jacqueline");
        user.setId(1L);
        user.setEmail("sofia@gmail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(getUser()));

        when(userRepository.save(user)).then(invocationOnMock -> invocationOnMock.<User>getArgument(0));

        UserDto userDto = new UserDto();
        userDto.setName("Jacqueline");
        userDto.setId(1L);
        userDto.setEmail("sofia@gmail.com");

        userService.update(userDto);

        verify(userRepository, times(1)).save(any());
    }


    User getUser() {
        User user = new User();
        user.setId(1L);
        user.setDni("123456789-1");
        user.setName("Sofia");
        user.setStatus("ACTIVO");
        return user;
    }
}