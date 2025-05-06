package turing.example.documentmanagement.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for the {@link DocumentNotFoundException} class.
 * <p>
 * This test ensures that the exception behaves correctly, including
 * message retrieval and exception handling.
 * </p>
 */
public class DocumentNotFoundExceptionTest {

    /**
     * Tests the creation of the exception with a custom message.
     * <p>
     * Ensures that the exception message is correctly stored and retrieved.
     * </p>
     */
    @Test
    void testExceptionMessage() {
        // Define custom message
        String errorMessage = "Document not found";

        // Create exception instance
        DocumentNotFoundException exception = new DocumentNotFoundException(errorMessage);

        // Verify the message is correctly set
        assertThat(exception.getMessage()).isEqualTo(errorMessage);
    }

    /**
     * Tests exception throwing and catching.
     * <p>
     * Ensures that the exception can be thrown and properly caught.
     * </p>
     */
    @Test
    void testExceptionThrowing() {
        // Define custom message
        String errorMessage = "Document with ID 123 not found";

        // Verify that the exception is thrown
        Exception thrownException = assertThrows(DocumentNotFoundException.class, () -> {
            throw new DocumentNotFoundException(errorMessage);
        });

        // Ensure the thrown exception contains the correct message
        assertThat(thrownException.getMessage()).isEqualTo(errorMessage);
    }
}
