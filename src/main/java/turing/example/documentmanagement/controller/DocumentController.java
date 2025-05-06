package turing.example.documentmanagement.controller;

import turing.example.documentmanagement.entity.Document;
import turing.example.documentmanagement.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for handling document-related operations in the Document Management System.
 * <p>
 * Provides RESTful endpoints for document management, including:
 * <ul>
 *     <li>Uploading documents</li>
 *     <li>Retrieving documents (can be added later)</li>
 *     <li>Deleting documents (can be added later)</li>
 * </ul>
 * </p>
 *
 * This controller interacts with the {@link DocumentService} to process document-related business logic.
 */
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    /**
     * Constructor-based dependency injection for DocumentService.
     *
     * @param documentService Service layer for handling document operations.
     */
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Endpoint to upload a document.
     *
     * <p>This method accepts a multipart file and the owner's name,
     * processes the document via {@link DocumentService}, and returns a response.</p>
     *
     * @param file  The document file to be uploaded.
     * @param owner The name of the owner of the document.
     * @return {@link ResponseEntity} containing the saved document details if successful,
     *         or an error response if the upload fails.
     */
    @PostMapping("/upload")
    public ResponseEntity<?> uploadDocument(@RequestParam("file") MultipartFile file,
                                            @RequestParam("owner") String owner) {
        try {
            // Validate if the file is empty before processing
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("File is empty. Please upload a valid document.");
            }

            // Call the service layer to handle document processing
            Document document = documentService.uploadDocument(file, owner);
            return ResponseEntity.status(HttpStatus.CREATED).body(document);

        } catch (Exception e) {
            // Log the exception for debugging (not included here, but should be in real-world apps)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while uploading the document: " + e.getMessage());
        }
    }
}
