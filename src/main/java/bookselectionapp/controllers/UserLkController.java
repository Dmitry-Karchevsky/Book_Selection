package bookselectionapp.controllers;

import bookselectionapp.adapters.BookListAdapter;
import bookselectionapp.entities.Book;
import bookselectionapp.entities.User;
import bookselectionapp.entities.Views;
import bookselectionapp.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lk")
@Api(value = "user lk")
public class UserLkController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id_user}")
    @ApiOperation(value = "show one user", response = ResponseEntity.class)
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

        if (!userService.isTrueUser(id))
            return ResponseEntity.notFound().build();

        User updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedUser);
        }
    }

    @PutMapping("/{id}/read_books")
    @JsonView(Views.RequiredField.class)
    public ResponseEntity<User> updateUsersReadBooks(@RequestBody Book readBook,
                                                     @PathVariable Long id) {

        if (!userService.isTrueUser(id))
            return ResponseEntity.notFound().build();

        User updatedUser = userService.updateUserReadBooks(id, readBook);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedUser);
        }
    }

    @PostMapping("/{id}/read_books")
    @JsonView(Views.RequiredField.class)
    public ResponseEntity<User> updateAllUsersReadBooks(@RequestBody BookListAdapter bookListAdapter,
                                                        @PathVariable Long id) {

        if (!userService.isTrueUser(id))
            return ResponseEntity.notFound().build();

        User updatedUser = userService.updateUserReadBooks(id, bookListAdapter);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedUser);
        }
    }

    @PostMapping("/{id}/liked_books")
    @JsonView(Views.RequiredField.class)
    public ResponseEntity<User> updateAllUsersLikedBooks(@RequestBody BookListAdapter bookListAdapter,
                                                        @PathVariable Long id) {
        if (!userService.isTrueUser(id))
            return ResponseEntity.notFound().build();

        User updatedUser = userService.updateUserLikedBooks(id, bookListAdapter);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedUser);
        }
    }
}
