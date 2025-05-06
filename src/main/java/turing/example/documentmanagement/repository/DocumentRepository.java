package turing.example.documentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import turing.example.documentmanagement.entity.Document;

import java.util.Optional;

/**
 * Repository interface for managing {@link Document} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing built-in methods for
 * performing CRUD operations on the `document` table in the database.
 * </p>
 *
 * <p>
 * Spring Data JPA will automatically generate the implementation for this repository,
 * allowing seamless interaction with the database.
 * </p>
 */
public interface DocumentRepository extends JpaRepository<Document, Long> {

    /**
     * Finds a document by its name.
     *
     * @param name the name of the document
     * @return an {@link Optional} containing the found document, if any
     */
    Optional<Document> findByName(String name);

    /**
     * Checks if a document with the given name exists.
     *
     * @param name the name of the document
     * @return {@code true} if a document with the given name exists, otherwise {@code false}
     */
    boolean existsByName(String name);
}
