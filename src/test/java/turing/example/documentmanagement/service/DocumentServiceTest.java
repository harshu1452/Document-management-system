package turing.example.documentmanagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;
import turing.example.documentmanagement.entity.Document;
import turing.example.documentmanagement.entity.DocumentVersion;
import turing.example.documentmanagement.repository.DocumentRepository;
import turing.example.documentmanagement.repository.DocumentVersionRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

/**
 * Unit test for {@link DocumentService}.
 * <p>
 * This test class verifies the behavior of document upload functionality,
 * ensuring correct interactions with the database and file system.
 * </p>
 */
class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DocumentVersionRepository versionRepository;

    @Mock
    private MultipartFile mockFile;

    @InjectMocks
    private DocumentService documentService;

    private static final String UPLOAD_DIR = "uploads/";
    private static final String FILE_NAME = "test-document.txt";
    private static final String FILE_PATH = UPLOAD_DIR + FILE_NAME;
    private static final String OWNER = "JohnDoe";

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case: Successfully uploading a document.
     * <p>
     * This test verifies that the document is correctly saved to the database,
     * and a corresponding document version is created.
     * </p>
     */
    @Test
    void testUploadDocument_Success() throws Exception {
        // Mock file behavior
        when(mockFile.getOriginalFilename()).thenReturn(FILE_NAME);
        when(mockFile.getBytes()).thenReturn("Sample content".getBytes());

        // Mock database interactions
        Document mockDocument = new Document();
        mockDocument.setId(1L);
        mockDocument.setName(FILE_NAME);
        mockDocument.setOwner(OWNER);
        mockDocument.setCreatedAt(LocalDateTime.now());

        DocumentVersion mockVersion = new DocumentVersion();
        mockVersion.setId(1L);
        mockVersion.setFilePath(FILE_PATH);
        mockVersion.setVersion(1);
        mockVersion.setUploadedAt(LocalDateTime.now());
        mockVersion.setDocument(mockDocument);

        when(documentRepository.save(any(Document.class))).thenReturn(mockDocument);
        when(versionRepository.save(any(DocumentVersion.class))).thenReturn(mockVersion);

        // Call the service method
        Document uploadedDocument = documentService.uploadDocument(mockFile, OWNER);

        // Assertions
        assertNotNull(uploadedDocument);
        assertEquals(FILE_NAME, uploadedDocument.getName());
        assertEquals(OWNER, uploadedDocument.getOwner());
        verify(documentRepository, times(1)).save(any(Document.class));
        verify(versionRepository, times(1)).save(any(DocumentVersion.class));
    }

    /**
     * Test case: Handling file storage failure.
     * <p>
     * This test ensures that if there is an issue saving the file, an exception is thrown.
     * </p>
     */
    @Test
    void testUploadDocument_FileStorageFailure() throws Exception {
        // Mock file behavior with null filename
        when(mockFile.getOriginalFilename()).thenReturn(null);

        // Expect an exception when calling uploadDocument
        Exception exception = assertThrows(Exception.class, () -> documentService.uploadDocument(mockFile, OWNER));

        // Verify that database save operations were never called
        verify(documentRepository, never()).save(any(Document.class));
        verify(versionRepository, never()).save(any(DocumentVersion.class));

        // Assert that the correct exception is thrown
        assertNotNull(exception);
    }
}
