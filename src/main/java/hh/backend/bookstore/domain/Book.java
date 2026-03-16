package hh.backend.bookstore.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Entity goes up here (Ex c3.1b)
public class Book {

    // Entity and ID added (Ex c3.1b)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // strategy for id is best as IDENTITY
    private Long id;

    // Class created (Ex c2.5)
    private String title;
    private String author;
    @Column(name="publish_year") // for handling "year" being reserved column name in SQL
    private Integer year;
    private String isbn;
    private Double price;

    public Book(String title, String author, Integer year, String isbn, Double price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.price = price;
    }

    public Book() {
        this.title = null;
        this.author = null;
        this.year = null; // default 0, null allowed only for Integer, not int
        this.isbn = null;
        this.price = null;
    }

    // as id was added later, it also needed getter and setters (Ex c3.1b)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // getters and setters generated
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", year=" + year + ", isbn=" + isbn + ", price=" + price
                + "]";
    }

    
}
