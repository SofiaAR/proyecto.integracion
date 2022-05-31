package cl.sofia.users.web;

import cl.sofia.users.model.dtos.UserDto;
import cl.sofia.users.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("name/{username}")
    public ResponseEntity<?> finByName(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByName(username));
    }

    @GetMapping("id/{userId}")
    public ResponseEntity<?> findById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

}
