package turing.example.documentmanagement.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import turing.example.documentmanagement.entity.Document;
import turing.example.documentmanagement.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Unit test for {@link DocumentController}.
 * <p>
 * This class verifies the behavior of the DocumentController's REST endpoints,
 * ensuring correct handling of file uploads and response statuses.
 * </p>
 */
class DocumentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DocumentService documentService;

    @InjectMocks
    private DocumentController documentController;

    @BeforeEach
    void setUp() {
        // Initialize mocks and setup the controller for testing
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(documentController).build();
    }

    /**
     * Test case: Successfully uploading a document.
     * <p>
     * This test verifies that a valid file upload request returns HTTP 201 Created
     * and the document details in the response.
     * </p>
     */
    @Test
    void testUploadDocument_Success() throws Exception {
        // Mock multipart file
        MockMultipartFile mockFile = new MockMultipartFile(
                "file", "test-document.txt", MediaType.TEXT_PLAIN_VALUE, "Sample content".getBytes()
        );

        // Mock Document response from service layer
        Document mockDocument = new Document();
        mockDocument.setId(1L);
        mockDocument.setName("test-document.txt");
        mockDocument.setOwner("JohnDoe");

        // Define service behavior
        when(documentService.uploadDocument(any(MultipartFile.class), eq("JohnDoe"))).thenReturn(mockDocument);

        // Perform the request
        mockMvc.perform(multipart("/documents/upload")
                        .file(mockFile)
                        .param("owner", "JohnDoe"))
                .andExpect(status().isCreated()) // Expect HTTP 201 Created
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("test-document.txt"))
                .andExpect(jsonPath("$.owner").value("JohnDoe"));

        // Verify service method was called
        verify(documentService, times(1)).uploadDocument(any(MultipartFile.class), eq("JohnDoe"));
    }

    /**
     * Test case: Uploading an empty file.
     * <p>
     * This test ensures that if an empty file is uploaded, the API responds with HTTP 400 Bad Request.
     * </p>
     */
    @Test
    void testUploadDocument_EmptyFile() throws Exception {
        // Mock an empty file
        MockMultipartFile emptyFile = new MockMultipartFile(
                "file", "empty.txt", MediaType.TEXT_PLAIN_VALUE, new byte[0]
        );

        // Perform the request
        mockMvc.perform(multipart("/documents/upload")
                        .file(emptyFile)
                        .param("owner", "JohnDoe"))
                .andExpect(status().isBadRequest()) // Expect HTTP 400 Bad Request
                .andExpect(content().string("File is empty. Please upload a valid document."));

        // Ensure service method is NOT called
        verify(documentService, never()).uploadDocument(any(MultipartFile.class), anyString());
    }

    /**
     * Test case: Handling an internal server error during document upload.
     * <p>
     * This test ensures that if an exception occurs while uploading a document,
     * the API responds with HTTP 500 Internal Server Error.
     * </p>
     */
    @Test
    void testUploadDocument_InternalServerError() throws Exception {
        // Mock file
        MockMultipartFile mockFile = new MockMultipartFile(
                "file", "test-document.txt", MediaType.TEXT_PLAIN_VALUE, "Sample content".getBytes()
        );

        // Simulate an exception in service layer
        when(documentService.uploadDocument(any(MultipartFile.class), eq("JohnDoe")))
                .thenThrow(new RuntimeException("Database error"));

        // Perform the request
        mockMvc.perform(multipart("/documents/upload")
                        .file(mockFile)
                        .param("owner", "JohnDoe"))
                .andExpect(status().isInternalServerError()) // Expect HTTP 500 Internal Server Error
                .andExpect(content().string("An error occurred while uploading the document: Database error"));

        // Verify service method was called once
        verify(documentService, times(1)).uploadDocument(any(MultipartFile.class), eq("JohnDoe"));
    }
}
