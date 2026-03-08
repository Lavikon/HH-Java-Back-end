package hh.backend.bookstore.domain;

import org.springframework.data.repository.CrudRepository;

// created for Ex c4.1

public interface CategoryRepository extends CrudRepository<Book,Long> {

}

