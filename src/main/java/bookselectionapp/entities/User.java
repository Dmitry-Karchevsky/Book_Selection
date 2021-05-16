package bookselectionapp.entities;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    @Id
    @Column(updatable = false)
    @GenericGenerator(name="users_seq", strategy = "UseExistingOrGenerateIdGenerator")
    @SequenceGenerator(name="users_seq", sequenceName="user_id_seq", allocationSize=1)
    @JsonView(Views.RequiredField.class)
    private Long id;

    @Column(nullable = false, length = 60, unique = true)
    @JsonView(Views.RequiredField.class)
    private String email;

    @Column(nullable = false, length = 100)
    @Size(min=3, message = "Не меньше 3 знаков")
    @JsonView(Views.RequiredField.class)
    private String password;

    @Column(nullable = false, length = 100)
    @JsonView(Views.RequiredField.class)
    private String name;

    @Column(nullable = false)
    @JsonView(Views.RequiredField.class)
    private Integer age;

    @Column(nullable = false, length = 30)
    @JsonView(Views.RequiredField.class)
    private String language;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonView(Views.RequiredField.class)
    private Set<Role> roles;

    @ManyToMany(mappedBy = "followersUsers", fetch = FetchType.EAGER)
    @JsonView(Views.NotRequiredField.class)
    private Set<Author> preferenceAuthors;

    @ManyToMany(mappedBy = "usersReadBook", fetch = FetchType.EAGER)
    @JsonView(Views.NotRequiredField.class)
    private Set<Book> readBooks;

    @ManyToMany(mappedBy = "usersPrefersBook", fetch = FetchType.EAGER)
    @JsonView(Views.NotRequiredField.class)
    private Set<Book> preferenceBooks;

    @ManyToMany(mappedBy = "usersLikedInCollectionBook", fetch = FetchType.EAGER)
    @JsonView(Views.NotRequiredField.class)
    private Set<Book> likedCollectionBooks;

    @ManyToMany(mappedBy = "usersPreferesGenre", fetch = FetchType.EAGER)
    @JsonView(Views.NotRequiredField.class)
    private Set<Genre> preferenceGenres;

    @ManyToMany(mappedBy = "usersPreferesCategory", fetch = FetchType.EAGER)
    @JsonView(Views.NotRequiredField.class)
    private Set<Category> preferenceCategories;

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Author> getPreferenceAuthors() {
        return preferenceAuthors;
    }

    public void setPreferenceAuthors(Set<Author> preferenceAuthors) {
        this.preferenceAuthors = preferenceAuthors;
    }

    public Set<Book> getReadBooks() {
        return readBooks;
    }

    public void setReadBooks(Set<Book> readBooks) {
        this.readBooks = readBooks;
    }

    public Set<Book> getPreferenceBooks() {
        return preferenceBooks;
    }

    public void setPreferenceBooks(Set<Book> preferenceBooks) {
        this.preferenceBooks = preferenceBooks;
    }

    public Set<Book> getLikedCollectionBooks() {
        return likedCollectionBooks;
    }

    public void setLikedCollectionBooks(Set<Book> likedCollectionBooks) {
        this.likedCollectionBooks = likedCollectionBooks;
    }

    public Set<Genre> getPreferenceGenres() {
        return preferenceGenres;
    }

    public void setPreferenceGenres(Set<Genre> preferenceGenres) {
        this.preferenceGenres = preferenceGenres;
    }

    public Set<Category> getPreferenceCategories() {
        return preferenceCategories;
    }

    public void setPreferenceCategories(Set<Category> preferenceCategories) {
        this.preferenceCategories = preferenceCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
