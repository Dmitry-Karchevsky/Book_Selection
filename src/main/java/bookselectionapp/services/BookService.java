package bookselectionapp.services;

import bookselectionapp.entities.Author;
import bookselectionapp.entities.Book;
import bookselectionapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void saveBook(Book newBook) {
        bookRepository.save(newBook);
    }
}
