package bookselectionapp.services;

import bookselectionapp.entities.*;
import bookselectionapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    public Book findBookById(Long idBook) {
        return bookRepository.findById(idBook).orElse(null);
    }

    public Book findBookByName(String name) {
        return bookRepository.findByName(name);
    }

    public List<Book> findBooksByGenre(Genre genre) {
        return bookRepository.findAllByGenre(genre);
    }

    public List<Book> findBooksByAuthor(Author author) {
        return bookRepository.findAllByAuthor(author);
    }

    public List<Book> findBooksByCategory(Category category) {
        return bookRepository.findAllByCategory(category);
    }

    public List<Book> getRecommendation(Long id){
        Set<Book> recommendationSet = new HashSet<>();
        User user = userService.findUserById(id);

        for (Genre genre : user.getPreferenceGenres()){
            recommendationSet.addAll(findBooksByGenre(genre));
        }
        for (Author author : user.getPreferenceAuthors()){
            recommendationSet.addAll(findBooksByAuthor(author));
        }
        for (Category category : user.getPreferenceCategories()){
            recommendationSet.addAll(findBooksByCategory(category));
        }

        recommendationSet.addAll(user.getPreferenceBooks());
        recommendationSet.addAll(user.getLikedCollectionBooks());

        recommendationSet.removeIf(book -> (book.getAgeCategory() != null) && user.getAge().compareTo(Integer.parseInt(book.getAgeCategory().substring(0, book.getAgeCategory().length()-1))) < 0);
        recommendationSet.removeIf(book -> user.getReadBooks().contains(book));
        return recommendationSet.stream().collect(Collectors.toList());
    }

    public List<Book> findAllBooksByName(String name) {
        return bookRepository.findAllByNameContains(name);
    }

    public void saveBook(Book newBook) {
        bookRepository.save(newBook);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
