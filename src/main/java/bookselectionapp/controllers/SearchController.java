package bookselectionapp.controllers;

import bookselectionapp.entities.Book;
import bookselectionapp.entities.Views;
import bookselectionapp.services.AuthorService;
import bookselectionapp.services.BookService;
import bookselectionapp.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@Api(value = "search")
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @GetMapping
    @JsonView(Views.RequiredField.class)
    public ResponseEntity<List<Book>> search(@RequestBody String searchString) {
        List<Book> searchBook = bookService.findAllBooksByName(searchString);

        if (searchBook == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(searchBook);
        }
    }
}
