package bookselectionapp.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private String ageCategory;

    @Column
    private String linkImage;

    @Column
    private Integer yearPublication;

    @Column(length = 20000)
    private String info;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> usersReadBook;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> usersPrefersBook;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> usersLikedInCollectionBook;

    public Book() {
    }

    public Book(String name, Author author) {
        this.name = name;
        this.author = author;
    }

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public Integer getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(Integer yearPublication) {
        this.yearPublication = yearPublication;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<User> getUsersReadBook() {
        return usersReadBook;
    }

    public void setUsersReadBook(Set<User> usersReadBook) {
        this.usersReadBook = usersReadBook;
    }

    public Set<User> getUsersPrefersBook() {
        return usersPrefersBook;
    }

    public void setUsersPrefersBook(Set<User> usersPrefersBook) {
        this.usersPrefersBook = usersPrefersBook;
    }

    public Set<User> getUsersLikedInCollectionBook() {
        return usersLikedInCollectionBook;
    }

    public void setUsersLikedInCollectionBook(Set<User> usersLikedInCollectionBook) {
        this.usersLikedInCollectionBook = usersLikedInCollectionBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", category=" + category +
                ", ageCategory=" + ageCategory +
                ", linkImage='" + linkImage + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
