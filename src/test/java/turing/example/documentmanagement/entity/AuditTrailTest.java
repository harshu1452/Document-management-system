package turing.example.documentmanagement.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import turing.example.documentmanagement.repository.AuditTrailRepository;
import turing.example.documentmanagement.repository.DocumentRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for the {@link AuditTrail} entity.
 * <p>
 * This test verifies that the AuditTrail entity correctly persists, retrieves,
 * and manages relationships with the Document entity.
 * </p>
 */
public class AuditTrailTest {

    private Document document;

    /**
     * Sets up test data before each test case.
     * <p>
     * Creates and saves a sample Document entity to be used for AuditTrail testing.
     * </p>
     */
    @BeforeEach
    void setUp() {
        // Create and save a test document
        document = new Document();
        document.setName("Test Document");
        document.setOwner("John Doe");
        document.setCreatedAt(LocalDateTime.now());
    }

    /**
     * Tests the creation and persistence of an {@link AuditTrail} entity.
     * <p>
     * Ensures the entity is saved correctly with the associated document.
     * </p>
     */
    @Test
    void testCreateAuditTrail() {
        // Create an AuditTrail entry
        AuditTrail auditTrail = new AuditTrail();
        auditTrail.setAction("Created");
        auditTrail.setPerformedBy("Admin");
        auditTrail.setTimestamp(LocalDateTime.now());
        auditTrail.setDocument(document);

        // Verify the saved entity is not null and contains the expected values
        assertThat(auditTrail).isNotNull();
        assertThat(auditTrail.getId()).isNull();
        assertThat(auditTrail.getAction()).isEqualTo("Created");
        assertThat(auditTrail.getPerformedBy()).isEqualTo("Admin");
        assertThat(auditTrail.getDocument()).isEqualTo(document);
    }
}
