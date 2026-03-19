package hh.backend.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.backend.bookstore.domain.AppUser;
import hh.backend.bookstore.domain.AppUserRepository;
import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;
import hh.backend.bookstore.domain.Category;
import hh.backend.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

    // commandlinerunner for saving categories and books merged into one (Ex c4.3b)
    @Bean
    public CommandLineRunner demo(CategoryRepository cRepository,BookRepository bRepository,AppUserRepository uRepository) {
        return (args) -> {
            // first create categories and save them
            Category scifi = cRepository.save(new Category("SciFi"));
            Category fantasy = cRepository.save(new Category("Fantasy"));
            Category fiction = cRepository.save(new Category("Fiction"));
            
            // next create the books
            bRepository.save(new Book("Farewell to arms, A", "Ernest Hemingway", 1929, "9780020519003", 19.99, fiction));
            bRepository.save(new Book("Animal Farm", "George Orwell", 1945, "9780194267533", 19.99, fiction));
            bRepository.save(new Book("Dune", "Frank Herbert", 1965, "9780441172719", 29.99, scifi));
            bRepository.save(new Book("1984", "George Orwell", 1949, "9780155658110", 19.99, fiction));
            bRepository.save(new Book("Foundation", "Isaac Asimov", 1951, "9780553293357", 24.99, scifi));
            bRepository.save(new Book("Fellowship of the Ring, The", "J.R.R. Tolkien", 1954, "9780008567125", 21.99, fantasy));

            // Print output to confirm input
            System.out.println("Categories in database:");
            for (Category category : cRepository.findAll()) {
                System.out.println(category.getCategoryid() + " " + category.getName());
            }

			System.out.println("Books in database:");
			for (Book book : bRepository.findAll()) {
				System.out.println(book.getTitle() + " by " + book.getAuthor() + " in " + book.getCategory());
			}
            
            // Adding users for securityDB with BCrypt (Ex c6.2d)
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            uRepository.save(new AppUser("user", encoder.encode("user"), "user@mail.com", "USER"));
            uRepository.save(new AppUser("admin", encoder.encode("admin"), "admin@mail.com", "ADMIN"));

            // Confirm users were created
            System.out.println("Users in database:");
            for (AppUser user : uRepository.findAll()) {
                System.out.println(user.getUsername() + " as " + user.getRole());
            }
		};  
    }
}
