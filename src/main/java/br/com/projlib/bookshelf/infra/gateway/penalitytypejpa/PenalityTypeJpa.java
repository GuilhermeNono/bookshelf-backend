package br.com.projlib.bookshelf.infra.gateway.penalitytypejpa;

import br.com.projlib.bookshelf.infra.gateway.penalityjpa.PenalityJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "penality_type")
@Getter
@Setter
public class PenalityTypeJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "penalityType")
    private Set<PenalityJpa> penalties;

}
