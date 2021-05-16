package bookselectionapp.controllers;

import bookselectionapp.entities.User;
import bookselectionapp.entities.Views;
import bookselectionapp.services.AuthorService;
import bookselectionapp.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/home")
public class HomePageController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorService authorService;

    @GetMapping
    @JsonView(Views.RequiredField.class)
    public List<User> readAll() {
        authorService.getAllBooks();
        return userService.findAllUsers();
    }
}
