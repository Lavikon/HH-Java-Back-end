package hh.backend.bookstore.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

    // Added in Ex c4.1

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryid;

    @Column(nullable = false)
    private String name;

    @JsonIgnore // adding JSON ignore to prevent looping (Ex c5.1a b)
    // Adding relationship to books (Ex c4.3a iv)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Book> books; // added list of books (Ex c4.3a ii.)

    // toString has been already modified, so books list was not added (Ex c4.3a v.)
    @Override
    public String toString() {
        return name;
    }

    public Category() {
        this.name = null;
    }

    public Category(String name) {
        this.name = name;
    }

    // setters and getters
    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // new getters and setters (Ex c4.3a iii.)
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
