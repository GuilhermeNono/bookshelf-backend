package br.com.projlib.bookshelf.infra.gateway.userlibraryjpa;

import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import br.com.projlib.bookshelf.infra.gateway.coursejpa.CourseJpa;
import br.com.projlib.bookshelf.infra.gateway.libpermissionjpa.LibPermissionJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.penalityjpa.PenalityJpa;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user_library")
@Getter
@Setter
public class UserLibraryJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = true)
    private String rmRa;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "fk_user_library_library", referencedColumnName = "id")
    private LibraryJpa library;

    @ManyToOne
    @JoinColumn(name = "fk_user_library_user_account", referencedColumnName = "id")
    private UserAccountJpa userAccount;

    @OneToMany(mappedBy = "userLibrary")
    private Set<PenalityJpa> penalties;

    @OneToMany(mappedBy = "userLibrary")
    private Set<BorrowingJpa> borrowings;

    @ManyToMany
    @JoinTable(
            name = "lib_permission_user_library",
            joinColumns = @JoinColumn(name = "fk_user_library_lib_permission", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fk_lib_permission_user_library", referencedColumnName = "id"))
    private Set<LibPermissionJpa> libraryPermissions;

    @ManyToMany
    @JoinTable(
            name = "user_library_course",
            joinColumns = @JoinColumn(name = "fk_user_library_course", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fk_course_user_library", referencedColumnName = "id"))
    private Set<CourseJpa> courses;

}
