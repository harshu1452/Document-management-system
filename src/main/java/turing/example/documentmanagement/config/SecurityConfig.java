package turing.example.documentmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Security configuration class for the Document Management System.
 * This class sets up basic authentication using Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Defines an in-memory user authentication service.
     * This is useful for development and testing purposes.
     *
     * @return UserDetailsService - In-memory user authentication manager.
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("admin") // Username for login
                .password(passwordEncoder.encode("admin123")) // Encrypted password
                .roles("ADMIN") // Assign ADMIN role
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Provides a password encoder bean that securely hashes passwords.
     * BCrypt is a strong hashing function to enhance security.
     *
     * @return PasswordEncoder - BCrypt password encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
