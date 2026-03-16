package hh.backend.bookstore.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;

@Controller
public class BookController {
    
    @GetMapping("/index")    // For handling index request (Ex c2.5)
    public String index(){
        return "index";
    }

    // Create the repository
    private BookRepository repository;

    public BookController(BookRepository repository) {
    this.repository = repository;
}
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
        return "bookform";   // reuse same form
    }
}
