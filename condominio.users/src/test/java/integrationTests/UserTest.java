package integrationTests;

import cl.sofia.users.CondominioUserApplication;
import cl.sofia.users.model.dtos.UserDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = CondominioUserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {

    private final WebTestClient webTestClient = WebTestClient
            .bindToServer()
            .baseUrl("http://localhost:8092/condominio/users/")
            .build();


    @Test
    @Order(1)
    void save() {
        UserDto user = new UserDto();
        user.setName("SOFIA");
        user.setEmail("sofia@gmail.com");
        user.setDni("17461331-1");
        user.setLastName("arevalo");

        webTestClient
                .post()
                .uri("save")
                .bodyValue(user)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @Order(2)
    void findByName() {


        UserDto userDto = webTestClient
                .get()
                .uri("name/SOFIA")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(UserDto.class)
                .getResponseBody().blockFirst();

        assertNotNull(userDto);
        assertEquals("SOFIA", userDto.getName());

    }

    @Test
    @Order(3)
    void findById() {


        UserDto userDto = webTestClient
                .get()
                .uri("id/1")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(UserDto.class)
                .getResponseBody().blockFirst();

        assertNotNull(userDto);
        assertEquals("SOFIA", userDto.getName());

    }

    @Test
    @Order(4)
    void findAll() {

        webTestClient
                .get()
                .uri("all")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(UserDto.class)
                .hasSize(1);
    }

    @Test
    @Order(5)
    void update() {
        UserDto user = new UserDto();
        user.setId(1L);
        user.setLastName("AREVALO");

        webTestClient
                .put()
                .uri("update")
                .bodyValue(user)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @Order(6)
    void delete() {
        webTestClient
                .delete()
                .uri("delete/1")
                .exchange()
                .expectStatus()
                .isOk();
    }


}
