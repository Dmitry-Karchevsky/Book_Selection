package bookselectionapp.controllers;

import bookselectionapp.entities.Genre;
import bookselectionapp.entities.Views;
import bookselectionapp.services.GenreService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@Api(value = "genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    @JsonView(Views.RequiredField.class)
    public List<Genre> readAll() {
        return genreService.getAllGenres();
    }
}
