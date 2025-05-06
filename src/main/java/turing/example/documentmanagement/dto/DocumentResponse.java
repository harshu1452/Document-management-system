package turing.example.documentmanagement.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) representing the response for document-related operations.
 * <p>
 * This class is used to return a structured response when retrieving document details.
 * It contains essential metadata about a document, such as its ID, name, owner,
 * creation timestamp, latest version, and file path.
 * </p>
 */
@Data
public class DocumentResponse {

    /**
     * Unique identifier for the document.
     */
    private Long id;

    /**
     * Name of the document.
     */
    private String name;

    /**
     * Owner of the document.
     */
    private String owner;

    /**
     * Timestamp indicating when the document was created.
     */
    private LocalDateTime createdAt;

    /**
     * The latest version number of the document.
     */
    private int latestVersion;

    /**
     * The file system path or URL where the document is stored.
     */
    private String filePath;
}
