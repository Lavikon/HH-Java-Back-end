package hh.backend.bookstore;

// Excluded imports were for In memory userlist replaced by userDB (c6.1 -> c6.2)

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
            .requestMatchers("/delete/**").hasAuthority("ADMIN") // replaces hasRole (Ex c6.2f)
            .anyRequest().authenticated()
        )
        .formLogin(formlogin -> formlogin
            .loginPage("/login")
            .defaultSuccessUrl("/booklist", true)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/login?logout") // logout sends user to Login page and ?logout for message
            .permitAll()
        );
        return http.build();
    }
    
    // for understanding encoded passwords (Ex c6.2e)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
