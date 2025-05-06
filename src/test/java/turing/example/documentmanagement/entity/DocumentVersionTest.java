package turing.example.documentmanagement.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for the {@link DocumentVersion} entity.
 * <p>
 * This test ensures that the getters, setters, and Lombok-generated methods
 * work correctly for proper document version management.
 * </p>
 */
public class DocumentVersionTest {

    /**
     * Tests the creation and property setting of a {@link DocumentVersion} object.
     * <p>
     * Ensures that all fields are correctly assigned and retrieved.
     * </p>
     */
    @Test
    void testDocumentVersionProperties() {
        // Create sample data
        Long id = 1L;
        String filePath = "/uploads/document-v1.pdf";
        int version = 1;
        LocalDateTime uploadedAt = LocalDateTime.now();

        Document document = new Document();
        document.setId(10L);
        document.setName("SampleDoc");

        // Create and set properties
        DocumentVersion docVersion = new DocumentVersion();
        docVersion.setId(id);
        docVersion.setFilePath(filePath);
        docVersion.setVersion(version);
        docVersion.setUploadedAt(uploadedAt);
        docVersion.setDocument(document);

        // Validate field values
        assertThat(docVersion.getId()).isEqualTo(id);
        assertThat(docVersion.getFilePath()).isEqualTo(filePath);
        assertThat(docVersion.getVersion()).isEqualTo(version);
        assertThat(docVersion.getUploadedAt()).isEqualTo(uploadedAt);
        assertThat(docVersion.getDocument()).isEqualTo(document);
    }

    /**
     * Tests Lombok-generated methods: equals(), hashCode(), and toString().
     * <p>
     * Ensures that two identical {@link DocumentVersion} objects are equal,
     * have the same hash code, and produce a valid string representation.
     * </p>
     */
    @Test
    void testLombokGeneratedMethods() {
        // Create two identical objects
        Document doc = new Document();
        doc.setId(20L);
        doc.setName("ProjectReport");

        DocumentVersion version1 = new DocumentVersion();
        version1.setId(2L);
        version1.setFilePath("/uploads/report-v1.pdf");
        version1.setVersion(1);
        version1.setUploadedAt(LocalDateTime.of(2024, 3, 21, 15, 0));
        version1.setDocument(doc);

        DocumentVersion version2 = new DocumentVersion();
        version2.setId(2L);
        version2.setFilePath("/uploads/report-v1.pdf");
        version2.setVersion(1);
        version2.setUploadedAt(LocalDateTime.of(2024, 3, 21, 15, 0));
        version2.setDocument(doc);

        // Verify equals() and hashCode()
        assertThat(version1).isEqualTo(version2);
        assertThat(version1.hashCode()).isEqualTo(version2.hashCode());

        // Verify toString() is not null or empty
        assertThat(version1.toString()).isNotNull();
        assertThat(version1.toString()).contains("report-v1.pdf");
    }
}
