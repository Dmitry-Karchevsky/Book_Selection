package bookselectionapp.controllers;

import bookselectionapp.entities.User;
import bookselectionapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
