package bookselectionapp.controllers;

import bookselectionapp.entities.Author;
import bookselectionapp.entities.User;
import bookselectionapp.entities.Views;
import bookselectionapp.services.AuthorService;
import bookselectionapp.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
@Api(value = "authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    @JsonView(Views.RequiredField.class)
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }
}
