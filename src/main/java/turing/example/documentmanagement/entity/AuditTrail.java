package turing.example.documentmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Entity representing an audit trail entry.
 * <p>
 * This entity logs actions performed on documents, providing traceability for changes.
 * It records details such as the action performed, the user responsible, the timestamp,
 * and the associated document.
 * </p>
 */
@Entity
@Data
public class AuditTrail {

    /**
     * Unique identifier for the audit trail entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The action performed (e.g., "Created", "Updated", "Deleted").
     */
    private String action;

    /**
     * The username or identifier of the user who performed the action.
     */
    private String performedBy;

    /**
     * The timestamp when the action was performed.
     */
    private LocalDateTime timestamp;

    /**
     * The document associated with this audit trail entry.
     * <p>
     * This establishes a many-to-one relationship, meaning multiple audit trail entries
     * can be linked to a single document.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;
}
