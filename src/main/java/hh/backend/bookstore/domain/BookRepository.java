package hh.backend.bookstore.domain;

import org.springframework.data.repository.CrudRepository;

// created for Ex c3.1

public interface BookRepository extends CrudRepository<Book,Long> {
    
}
