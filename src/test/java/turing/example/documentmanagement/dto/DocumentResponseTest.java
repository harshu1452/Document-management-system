package turing.example.documentmanagement.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for the {@link DocumentResponse} DTO.
 * <p>
 * This test ensures that the getters, setters, and Lombok-generated methods
 * work correctly for proper data transfer.
 * </p>
 */
public class DocumentResponseTest {

    /**
     * Tests the creation and property setting of a {@link DocumentResponse} object.
     * <p>
     * Ensures that all fields are correctly assigned and retrieved.
     * </p>
     */
    @Test
    void testDocumentResponseProperties() {
        // Create sample data
        Long id = 1L;
        String name = "Test Document";
        String owner = "JohnDoe";
        LocalDateTime createdAt = LocalDateTime.now();
        int latestVersion = 2;
        String filePath = "/uploads/test-document.pdf";

        // Create and set properties
        DocumentResponse response = new DocumentResponse();
        response.setId(id);
        response.setName(name);
        response.setOwner(owner);
        response.setCreatedAt(createdAt);
        response.setLatestVersion(latestVersion);
        response.setFilePath(filePath);

        // Validate field values
        assertThat(response.getId()).isEqualTo(id);
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getOwner()).isEqualTo(owner);
        assertThat(response.getCreatedAt()).isEqualTo(createdAt);
        assertThat(response.getLatestVersion()).isEqualTo(latestVersion);
        assertThat(response.getFilePath()).isEqualTo(filePath);
    }

    /**
     * Tests Lombok-generated methods: equals(), hashCode(), and toString().
     * <p>
     * Ensures that two identical {@link DocumentResponse} objects are equal,
     * have the same hash code, and produce a valid string representation.
     * </p>
     */
    @Test
    void testLombokGeneratedMethods() {
        // Create two identical objects
        DocumentResponse response1 = new DocumentResponse();
        response1.setId(1L);
        response1.setName("SampleDoc");
        response1.setOwner("Alice");
        response1.setCreatedAt(LocalDateTime.of(2024, 3, 21, 10, 0));
        response1.setLatestVersion(3);
        response1.setFilePath("/uploads/sample.pdf");

        DocumentResponse response2 = new DocumentResponse();
        response2.setId(1L);
        response2.setName("SampleDoc");
        response2.setOwner("Alice");
        response2.setCreatedAt(LocalDateTime.of(2024, 3, 21, 10, 0));
        response2.setLatestVersion(3);
        response2.setFilePath("/uploads/sample.pdf");

        // Verify equals() and hashCode()
        assertThat(response1).isEqualTo(response2);
        assertThat(response1.hashCode()).isEqualTo(response2.hashCode());

        // Verify toString() is not null or empty
        assertThat(response1.toString()).isNotNull();
        assertThat(response1.toString()).contains("SampleDoc");
    }
}
