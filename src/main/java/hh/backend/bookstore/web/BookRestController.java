package hh.backend.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@CrossOrigin
@Controller
@RequestMapping("/rest") // adds "/rest" to all endpoints
public class BookRestController {

    // repo
    private final BookRepository bRepository;
    // repo constructor
    public BookRestController(BookRepository bRepository) {
        this.bRepository = bRepository;
    }
    
    // REST to get all books (Ex c5.1a)
    @GetMapping("/books")
    public @ResponseBody List<Book> findAllBooksRest() {
        return (List<Book>) bRepository.findAll();
    }
    
    // REST to get book by id (Ex c5.1b)
    @GetMapping("/books/{id}")
    public @ResponseBody Optional<Book> getOneBookRest(@PathVariable(name = "id") Long bookId) {
        return bRepository.findById(bookId);
    }
}
