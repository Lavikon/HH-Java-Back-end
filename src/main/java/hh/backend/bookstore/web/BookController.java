package hh.backend.bookstore.web;
/* Replaced by Repo
import java.util.ArrayList;
import java.util.List;
*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;

@Controller
public class BookController {
    
    @GetMapping("/index")    // For handling index request (Ex c2.5)
    public String index(){
        return "index";
    }

    // later segments will be used for other exercises

    // // private List<Book> books = new ArrayList<Book>(); // replaced by repo
    // private BookRepository repository;

    // @GetMapping("/booklist")
    // public String getBooks(Model model){
    //     // model.addAttribute("books", books); // replaced by repo
    //     model.addAttribute("books", repository.findAll());
    //     return "booklist";
    // }

    // // Add a book form
    // @GetMapping("/add")
    // public String addBook(Model model) {
    //     model.addAttribute("book", new Book());
    //     return "addbook";
    // }

    // // Save new book
    // @PostMapping("/save")
    // public String save(Book book) {
    //     repository.save(book);
    //     return "redirect:/booklist";
    // }

    // // Delete book (Ex c3.3)
    // @GetMapping("/delete/{id}")
    // public String deleteBook(@PathVariable("id") Long id) {
    //     repository.deleteById(id);
    //     return "redirect:/booklist";
    // }

    // // Edit book (Ex c3.4)
    // @GetMapping("/edit/{id}")
    // public String editBook(@PathVariable("id") Long id, Model model) {
    //     Book book = repository.findById(id).orElse(null);
    //     model.addAttribute("book", book);
    //     return "addbook";   // reuse same form
    // }
}
