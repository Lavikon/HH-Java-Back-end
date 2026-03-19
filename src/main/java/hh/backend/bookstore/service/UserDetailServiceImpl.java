package hh.backend.bookstore.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.backend.bookstore.domain.AppUser;
import hh.backend.bookstore.domain.AppUserRepository;

// This was created for Ex c6.2c

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    
    // repos
    private final AppUserRepository repository;
    public UserDetailServiceImpl(AppUserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser currUser = repository.findByUsername(username);
        UserDetails user = new User(username, currUser.getPassword(),AuthorityUtils.createAuthorityList(currUser.getRole()));

        return user;
    }
}
