package hh.backend.bookstore.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;
import hh.backend.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
    
    @GetMapping("/index")    // For handling index request (Ex c2.5)
    public String index(){
        return "index";
    }

    // DATABASE
    // add the repositories
    private BookRepository repository; // (Ex c3.2)
    private CategoryRepository cRepository; // (Ex c4.4)

    public BookController(BookRepository repository,CategoryRepository cRepository) {
        this.repository = repository;
        this.cRepository = cRepository;
    }

    // CRUD
    // Display booklist (Ex c3.2)
    @GetMapping("/booklist")
    public String getBooks(Model model){
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
    // Add a book form (Ex c3.3)
    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", cRepository.findAll()); // added to pass categories to form (Ex c4.4)
        return "bookform";
    }
    // Save new book (Ex c3.3)
    @PostMapping("/save")
    public String save(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }
    // Delete book (Ex c3.3)
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/booklist";
    }
    // Edit book (Ex c3.4)
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = repository.findById(id).orElse(null);
        model.addAttribute("book", book);
        model.addAttribute("categories", cRepository.findAll()); // added to pass categories to form (Ex c4.4)
        return "bookform";   // reuse same form
    }

    // SECURITY
    // Login (Ex c6.1c)
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
