package hh.backend.bookstore.domain;

import org.springframework.data.repository.CrudRepository;

// created for Ex c6.2b

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
