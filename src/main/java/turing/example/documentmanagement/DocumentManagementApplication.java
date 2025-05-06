package turing.example.documentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Document Management System application.
 * <p>
 * This class initializes the Spring Boot application and starts the embedded server.
 * It serves as the primary configuration and bootstrap class for the entire application.
 * </p>
 */
@SpringBootApplication
public class DocumentManagementApplication {

    /**
     * The main method that bootstraps the Spring Boot application.
     * <p>
     * It calls {@link SpringApplication#run(Class, String...)} to launch the application
     * with the provided configuration.
     * </p>
     *
     * @param args Command-line arguments passed during application startup.
     */
    public static void main(String[] args) {
        SpringApplication.run(DocumentManagementApplication.class, args);
    }
}