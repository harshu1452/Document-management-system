package turing.example.documentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import turing.example.documentmanagement.entity.DocumentVersion;

import java.util.List;

/**
 * Repository interface for managing {@link DocumentVersion} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing built-in methods for
 * performing CRUD operations on the `document_version` table in the database.
 * </p>
 *
 * <p>
 * Spring Data JPA automatically implements this interface, allowing for
 * easy interaction with document version records.
 * </p>
 */
public interface DocumentVersionRepository extends JpaRepository<DocumentVersion, Long> {

    /**
     * Retrieves all versions of a document sorted in descending order.
     * <p>
     * This method helps in fetching the latest version first, which is useful for
     * version tracking and document retrieval purposes.
     * </p>
     *
     * @param documentId the ID of the document
     * @return a list of {@link DocumentVersion} sorted by version in descending order
     */
    List<DocumentVersion> findByDocumentIdOrderByVersionDesc(Long documentId);
}
