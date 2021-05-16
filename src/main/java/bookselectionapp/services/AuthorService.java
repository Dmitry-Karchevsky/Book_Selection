package bookselectionapp.services;

import bookselectionapp.entities.Author;
import bookselectionapp.entities.Book;
import bookselectionapp.repositories.AuthorRepository;
import bookselectionapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public void saveAuthor(Author newAuthor) {
        if (authorRepository.findByName(newAuthor.getName()) != null) {
            return;
        }
        authorRepository.save(newAuthor);
    }

    public Author findAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    public void getAllBooks() {
        
    }
}
