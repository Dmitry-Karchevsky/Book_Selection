package bookselectionapp.repositories;

import bookselectionapp.entities.Author;
import bookselectionapp.entities.Book;
import bookselectionapp.entities.Category;
import bookselectionapp.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor(Author author);
}
