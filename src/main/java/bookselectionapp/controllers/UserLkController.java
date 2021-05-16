package bookselectionapp.controllers;

import bookselectionapp.entities.User;
import bookselectionapp.entities.Views;
import bookselectionapp.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lk")
public class UserLkController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id_user}")
    public ResponseEntity<User> getUser(@PathVariable("id_user") Long id) {
        if (!userService.isTrueUser(id))
            return ResponseEntity.notFound().build();

        User userFromDB = userService.findUserById(id);
        if (userFromDB == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userFromDB);
        }
    }

    @PutMapping("/{id}")
    @JsonView(Views.RequiredField.class)
    public ResponseEntity<User> updateUser(@RequestBody User user,
                                           @PathVariable Long id) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedUser);
        }
    }
}
