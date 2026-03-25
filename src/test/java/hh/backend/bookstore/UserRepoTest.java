package hh.backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import hh.backend.bookstore.domain.AppUser;
import hh.backend.bookstore.domain.AppUserRepository;

@DataJpaTest
public class UserRepoTest {

    @Autowired
    private AppUserRepository repository;

    private AppUser testUser;
    private Long id;
    
    @BeforeEach
    public void setUp() {
        testUser = new AppUser("test","test",null,"TEST");
        repository.save(testUser);
        id = testUser.getId();
    }

    // CRUD tests
    @Test
    public void createdNew() throws Exception {
        AppUser test = repository.findById(id).orElse(null);
        
        assertThat(test.getId()).isNotNull();
    }
    @Test
    public void readTest() throws Exception {
        AppUser test = repository.findById(id).orElse(null);

        assertNotNull(test);
        assertEquals("test",test.getUsername());

    }
    @Test
    public void updateTest() throws Exception {
        testUser.setUsername("updated");
        repository.save(testUser);
        
        AppUser test = repository.findById(id).orElse(null);

        assertNotNull(test);
        assertEquals("updated", test.getUsername());
    }
    @Test
    public void deleteTest() throws Exception {
        repository.deleteById(id);
        assertThat(repository.findById(id)).isEmpty();
    }
}
