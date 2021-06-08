package bookselectionapp.controllers;

import bookselectionapp.entities.Book;
import bookselectionapp.services.BookService;
import bookselectionapp.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommend")
@Api(value = "recommend")
public class RecommendationController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/{id_user}")
    @ApiOperation(value = "show user recommendations", response = ResponseEntity.class)
    public ResponseEntity<List<Book>> getUser(@PathVariable("id_user") Long idUser) {
        if (!userService.isTrueUser(idUser))
            return ResponseEntity.notFound().build();

        List<Book> recommendBookList = bookService.getRecommendation(idUser);
        if (recommendBookList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(recommendBookList);
        }
    }
}
