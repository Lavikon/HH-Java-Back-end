package hh.backend.bookstore;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    // URL security access (Ex c6.1b)
    @Bean
    public SecurityFilterChain secFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/css/**").permitAll() // access to CSS 
            .requestMatchers("/delete/**").hasRole("ADMIN") // restricted delete to ADMIN (Ex c6.1f)
            .anyRequest().authenticated()
        )
        .formLogin(formlogin -> formlogin
            .loginPage("/login")
            .defaultSuccessUrl("/booklist", true)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/login")
            .permitAll()
        );
        return http.build();
    }

    // In memory user list (Ex c6.1b)
    @Bean
    public UserDetailsService userDetailsService() {
        // list to save users in
        List<UserDetails> users = new ArrayList<>();

        // Encode passwords
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // create users
        UserDetails user1 = User
            .withUsername("user")
            .password(passwordEncoder.encode("user"))
            .roles("USER")
            .build();
        users.add(user1);
        
        // create admin
        UserDetails admin1 = User
            .withUsername("admin")
            .password(passwordEncoder.encode("admin"))
            .roles("USER", "ADMIN")
            .build();
        users.add(admin1);

        return new InMemoryUserDetailsManager(users);
    }
}
