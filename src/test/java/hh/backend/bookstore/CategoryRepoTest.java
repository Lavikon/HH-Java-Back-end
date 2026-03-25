package hh.backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import hh.backend.bookstore.domain.Category;
import hh.backend.bookstore.domain.CategoryRepository;

@DataJpaTest
public class CategoryRepoTest {

    @Autowired
    private CategoryRepository repository;

    private Category testCategory;
    private Long id;

    @BeforeEach
    public void setUp() {
        testCategory = new Category("test");
        repository.save(testCategory);
        id = testCategory.getCategoryid();
    }

    // CRUD tests
    @Test
    public void createdNew() throws Exception {
        Category test = repository.findById(id).orElse(null);

        assertThat(test.getCategoryid()).isNotNull();
    }
    @Test
    public void readTest() throws Exception {
        Category test = repository.findById(id).orElse(null);

        assertNotNull(test);
        assertEquals("test",test.getName());

    }
    @Test
    public void updateTest() throws Exception {
        testCategory.setName("updated");
        repository.save(testCategory);
        
        Category test = repository.findById(id).orElse(null);

        assertNotNull(test);
        assertEquals("updated", test.getName());
    }
    @Test
    public void deleteTest() throws Exception {
        repository.deleteById(id);
        assertThat(repository.findById(id)).isEmpty();
    }
}
