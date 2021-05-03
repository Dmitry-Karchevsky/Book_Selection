package bookselectionapp.repositories;

import bookselectionapp.entities.Author;
import bookselectionapp.entities.Book;
import bookselectionapp.entities.Category;
import bookselectionapp.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAuthor(Author author);
    List<Book> findAllByName(String name);
    List<Book> findAllByGenre(Genre genre);
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByLanguage(String language);
    List<Book> findAllByAgeCategory(Integer ageCategory);
}
