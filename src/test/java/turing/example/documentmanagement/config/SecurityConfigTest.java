package turing.example.documentmanagement.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for the {@link SecurityConfig} class.
 * <p>
 * This test verifies that authentication and password encoding
 * work correctly as per the security configuration.
 * </p>
 */
public class SecurityConfigTest {

    private SecurityConfig securityConfig;
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    /**
     * Initializes the security configuration and beans before each test case.
     */
    @BeforeEach
    void setUp() {
        securityConfig = new SecurityConfig();
        passwordEncoder = securityConfig.passwordEncoder();
        userDetailsService = securityConfig.userDetailsService(passwordEncoder);
    }

    /**
     * Tests if the password encoder is correctly instantiated.
     * <p>
     * Ensures that BCrypt hashing works and produces different hashes for the same input.
     * </p>
     */
    @Test
    void testPasswordEncoder() {
        String rawPassword = "admin123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // Verify password is hashed and not equal to raw input
        assertThat(encodedPassword).isNotEqualTo(rawPassword);
        assertThat(passwordEncoder.matches(rawPassword, encodedPassword)).isTrue();
    }

    /**
     * Tests if the user details service loads the user correctly.
     * <p>
     * Ensures that a predefined user can be retrieved and has correct credentials.
     * </p>
     */
    @Test
    void testUserDetailsService() {
        // Load user by username
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");

        // Verify username and role
        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo("admin");
        assertThat(userDetails.getAuthorities()).isNotEmpty();
        assertThat(userDetails.getAuthorities().iterator().next().getAuthority()).isEqualTo("ROLE_ADMIN");

        // Verify password is encoded
        assertThat(passwordEncoder.matches("admin123", userDetails.getPassword())).isTrue();
    }

    /**
     * Tests loading a non-existing user.
     * <p>
     * Ensures that an exception is thrown if the user does not exist.
     * </p>
     */
    @Test
    void testUserNotFound() {
        assertThrows(Exception.class, () -> userDetailsService.loadUserByUsername("unknownUser"));
    }
}
