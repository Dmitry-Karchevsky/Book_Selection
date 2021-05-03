package bookselectionapp.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean isFiction;

    @OneToMany(mappedBy = "genre")
    private Set<Category> categories;

    @OneToMany(mappedBy = "genre")
    private Set<Book> books;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> usersPreferesGenre;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Author> authorsGenres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFiction() {
        return isFiction;
    }

    public void setFiction(Boolean fiction) {
        isFiction = fiction;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<User> getUsersPreferesGenre() {
        return usersPreferesGenre;
    }

    public void setUsersPreferesGenre(Set<User> usersPrefereGenre) {
        this.usersPreferesGenre = usersPrefereGenre;
    }

    public Set<Author> getAuthorsGenres() {
        return authorsGenres;
    }

    public void setAuthorsGenres(Set<Author> authorsGenres) {
        this.authorsGenres = authorsGenres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
