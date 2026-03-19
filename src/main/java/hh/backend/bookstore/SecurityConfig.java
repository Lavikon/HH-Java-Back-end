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
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
            // .requestMatchers("/delete/**").hasRole("ADMIN") // restricted delete to ADMIN (Ex c6.1f)
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

    // In memory user list (Ex c6.1b)
    // Replaced by AppUser entity+repo, UserDetailService and commandlinerunner (Ex c6.2e)

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     // list to save users in
    //     List<UserDetails> users = new ArrayList<>();

    //     // Encode passwords
    //     PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    //     // create users
    //     UserDetails user1 = User
    //         .withUsername("user")
    //         .password(passwordEncoder.encode("user"))
    //         .roles("USER")
    //         .build();
    //     users.add(user1);
        
    //     // create admin
    //     UserDetails admin1 = User
    //         .withUsername("admin")
    //         .password(passwordEncoder.encode("admin"))
    //         .roles("USER", "ADMIN")
    //         .build();
    //     users.add(admin1);

    //     return new InMemoryUserDetailsManager(users);
    // }
}
