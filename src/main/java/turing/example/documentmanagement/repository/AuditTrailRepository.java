package turing.example.documentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import turing.example.documentmanagement.entity.AuditTrail;

/**
 * Repository interface for managing {@link AuditTrail} entities.
 * <p>
 * This interface extends {@link JpaRepository}, providing built-in methods for
 * performing CRUD operations on the `audit_trail` table in the database.
 * </p>
 *
 * <p>
 * Spring Data JPA will automatically generate the implementation for this repository,
 * allowing easy interaction with the database.
 * </p>
 */
public interface AuditTrailRepository extends JpaRepository<AuditTrail, Long> {
    // Additional custom query methods can be defined here if needed
}
