package turing.example.documentmanagement.exception;

/**
 * Custom exception thrown when a requested document is not found in the system.
 * <p>
 * This exception extends {@link RuntimeException}, allowing it to be used
 * as an unchecked exception in service and controller layers.
 * </p>
 */
public class DocumentNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code DocumentNotFoundException} with a detailed message.
     *
     * @param message A descriptive message explaining why the exception occurred.
     */
    public DocumentNotFoundException(String message) {
        super(message);
    }
}
