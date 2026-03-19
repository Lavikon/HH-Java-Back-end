package hh.backend.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.bookstore.domain.Category;
import hh.backend.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {


    // repos 
    private final CategoryRepository repository;
    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    // display categories (Ex c4.2)
    @GetMapping("/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", repository.findAll());
        return "categorylist";
    }
   
    // add categories (Ex c4.2) 
    @GetMapping("/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "categoryform";
    }
    
    // Save new category (Ex c4.2)
    @PostMapping("/savecat")
    public String save(Category category) {
        repository.save(category);
        return "redirect:/categories";
    }
}
