package hh.backend.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;
import hh.backend.bookstore.domain.Category;
import hh.backend.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

 	@Bean
    public CommandLineRunner bookDemo(BookRepository repository) {
        return (args) -> {

            repository.save(new Book("Farewell to arms, A", "Ernest Hemingway", 1929, "9780020519003", 19.99));
            repository.save(new Book("Animal Farm", "George Orwell", 1945, "9780194267533", 19.99));
            repository.save(new Book("Dune", "Frank Herbert", 1965, "9780441172719", 29.99));
            repository.save(new Book("1984", "George Orwell", 1949, "9780155658110", 19.99));
            repository.save(new Book("Foundation", "Isaac Asimov", 1951, "9780553293357", 24.99));

			System.out.println("Books in database:");
			for (Book book : repository.findAll()) {
				System.out.println(book.getTitle() + " by " + book.getAuthor());
			}
        };

    }
/* Does not work
    @Bean
    public CommandLineRunner categoryDemo(CategoryRepository repository) {
        return (args) -> {

            repository.save(new Category("SciFi"));
            repository.save(new Category("Comic"));
            repository.save(new Category("Fantasy"));
            repository.save(new Category("Horror"));
            / * 
            System.out.println("Categories in database:");
            for (Category category : repository.findAll()) {
                System.out.println(category.getCategoryid() + " " + category.getName());
            }
* /
		};  
    }   Does not work */
}
