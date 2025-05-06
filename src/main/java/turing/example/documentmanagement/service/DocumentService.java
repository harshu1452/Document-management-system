package turing.example.documentmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import turing.example.documentmanagement.entity.Document;
import turing.example.documentmanagement.entity.DocumentVersion;
import turing.example.documentmanagement.repository.DocumentRepository;
import turing.example.documentmanagement.repository.DocumentVersionRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * Service class responsible for managing document-related operations.
 * <p>
 * This class provides methods to handle document uploads, store document metadata,
 * and maintain version history.
 * </p>
 */
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentVersionRepository versionRepository;

    // Directory where uploaded files will be stored
    private static final String UPLOAD_DIR = "uploads/";

    /**
     * Constructor-based dependency injection for the repositories.
     *
     * @param documentRepository     Repository for managing document entities.
     * @param versionRepository      Repository for managing document version entities.
     */
    public DocumentService(DocumentRepository documentRepository, DocumentVersionRepository versionRepository) {
        this.documentRepository = documentRepository;
        this.versionRepository = versionRepository;
    }

    /**
     * Uploads a new document and saves its metadata along with version tracking.
     *
     * <p>
     * The method performs the following steps:
     * <ul>
     *     <li>Ensures the upload directory exists before storing the file.</li>
     *     <li>Stores the file in the designated upload directory.</li>
     *     <li>Creates a new {@link Document} entity and persists it.</li>
     *     <li>Creates the first {@link DocumentVersion} and associates it with the document.</li>
     * </ul>
     * </p>
     *
     * @param file  The document file to be uploaded.
     * @param owner The owner of the document.
     * @return The saved {@link Document} entity with metadata.
     * @throws Exception If file storage fails.
     */
    public Document uploadDocument(MultipartFile file, String owner) throws Exception {
        // Define the upload directory path
        Path uploadDir = Paths.get(UPLOAD_DIR);

        // Ensure the uploads directory exists
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // Define the file path within the uploads directory
        Path filePath = uploadDir.resolve(file.getOriginalFilename());

        // Save the file to the specified directory
        Files.write(filePath, file.getBytes());

        // Create a new document entity
        Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setOwner(owner);
        document.setCreatedAt(LocalDateTime.now());

        // Save document metadata to the database
        Document savedDocument = documentRepository.save(document);

        // Create an initial version entry for the document
        DocumentVersion version = new DocumentVersion();
        version.setFilePath(filePath.toString());
        version.setVersion(1);
        version.setUploadedAt(LocalDateTime.now());
        version.setDocument(savedDocument);

        // Save the document version information
        versionRepository.save(version);

        return savedDocument;
    }
}
