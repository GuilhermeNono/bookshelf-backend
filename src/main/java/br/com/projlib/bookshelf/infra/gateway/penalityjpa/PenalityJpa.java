package br.com.projlib.bookshelf.infra.gateway.penalityjpa;

import br.com.projlib.bookshelf.infra.gateway.penalitytypejpa.PenalityTypeJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryjpa.UserLibraryJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "penality")
@Getter
@Setter
public class PenalityJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "fk_penality_user_library", referencedColumnName = "id")
    private UserLibraryJpa userLibrary;

    @ManyToOne
    @JoinColumn(name = "fk_penality_penality_type", referencedColumnName = "id")
    private PenalityTypeJpa penalityType;

}
