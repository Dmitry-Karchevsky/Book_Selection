package bookselectionapp.entities;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView(Views.RequiredField.class)
    private Long id;

    @Column(length = 100, unique = true)
    @JsonView(Views.RequiredField.class)
    private String name;

    @Column
    @JsonView(Views.RequiredField.class)
    private String linkImage;

    @Column(length = 20000)
    @JsonView(Views.RequiredField.class)
    private String info;

    @OneToMany(mappedBy = "author")
    @JsonView(Views.NotRequiredField.class)
    private Set<Book> books;

    @ManyToMany(mappedBy = "preferenceAuthors", fetch = FetchType.EAGER)
    @JsonView(Views.NotRequiredField.class)
    private Set<User> followersUsers;

    @ManyToMany(mappedBy = "authorsGenres", fetch = FetchType.EAGER)
    @JsonView(Views.NotRequiredField.class)
    private Set<Genre> preferenceGenres;

    public Author() {
    }

    public Author(String name, String info) {
        this.name = name;
        this.info = info;
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

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBookInSet(Book book) {
        this.books.add(book);
    }

    public Set<User> getFollowersUsers() {
        return followersUsers;
    }

    public void setFollowersUsers(Set<User> followersUsers) {
        this.followersUsers = followersUsers;
    }

    public Set<Genre> getPreferenceGenres() {
        return preferenceGenres;
    }

    public void setPreferenceGenres(Set<Genre> preferenceGenres) {
        this.preferenceGenres = preferenceGenres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", linkImage='" + linkImage + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
