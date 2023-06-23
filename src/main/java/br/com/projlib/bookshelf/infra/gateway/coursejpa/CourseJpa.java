package br.com.projlib.bookshelf.infra.gateway.coursejpa;

import br.com.projlib.bookshelf.infra.gateway.userlibLibraryCourseLinkJpa.UserLibraryCourseLinkJpa;
import jakarta.persistence.CascadeType;
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
import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
public class CourseJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "class", nullable = false)
    private String classroom;

    @Column(columnDefinition = "TINYINT", length = 1, nullable = false)
    private boolean active;

    @Column(nullable = false)
    private String period;

    @Column
    private String module;

    @OneToMany(mappedBy = "courses", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserLibraryCourseLinkJpa> userLibraryCourseLinks;

}
