package hh.backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    // create a shared param for testing
    private Book testBook;
    private Long id;

    // create the test book
    @BeforeEach
    public void setUp() {
        testBook = new Book("title", "author", 2000, null, null, null);
        repository.save(testBook);
        id = testBook.getId();
    }

    // CRUD tests
    // Create
    @Test
    public void createNewBook() throws Exception {
        assertThat(testBook.getId()).isNotNull();
    }

    // Read
    @Test
    public void readBook() throws Exception {
        Book readBook = repository.findById(id).orElse(null);

        assertNotNull(readBook);
        assertEquals("title",readBook.getTitle());
    }

    // Update
    @Test
    public void updateBook() throws Exception {
        testBook.setTitle("new title");
        repository.save(testBook);

        Book updateBook = repository.findById(id).orElse(null);

        assertNotNull(updateBook);
        assertThat(updateBook.getTitle()).contains("new title");
    }

    // Delete
    @Test
    public void deleteBook() throws Exception {
        repository.deleteById(id);
        assertThat(repository.findById(id)).isEmpty();
    }
}
