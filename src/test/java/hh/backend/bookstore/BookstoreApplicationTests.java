package hh.backend.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.backend.bookstore.web.BookController;
import hh.backend.bookstore.web.BookRestController;
import hh.backend.bookstore.web.CategoryController;

@SpringBootTest
class BookstoreApplicationTests {
	
	// Autowired still in use for testing
	@Autowired
	private BookController bookController;
	@Autowired
	private CategoryController categoryController;
	@Autowired
	private BookRestController bRestController;

	// Smoketests for controllers
	@Test
	public void contextLoads() throws Exception {
		assertThat(bookController).isNotNull();
		assertThat(categoryController).isNotNull();
		assertThat(bRestController).isNotNull();
	}
}
