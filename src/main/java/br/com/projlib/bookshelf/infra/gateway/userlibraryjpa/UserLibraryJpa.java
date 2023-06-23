package br.com.projlib.bookshelf.infra.gateway.userlibraryjpa;

import br.com.projlib.bookshelf.infra.gateway.borrowingjpa.BorrowingJpa;
import br.com.projlib.bookshelf.infra.gateway.coursejpa.CourseJpa;
import br.com.projlib.bookshelf.infra.gateway.libraryjpa.LibraryJpa;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibLibraryCourseLinkJpa.UserLibraryCourseLinkJpa;
import br.com.projlib.bookshelf.infra.gateway.userlibraryprofileJpa.UserLibraryProfileJpa;
import jakarta.persistence.CascadeType;
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
import java.util.List;

@Entity
@Table(name = "user_library")
@Getter
@Setter
public class UserLibraryJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TINYINT", length = 1, nullable = false)
    private boolean active;

    @Column
    private String rmRa;

    @Column
    private String profilePicture;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "fk_user_library_user_library_profile", referencedColumnName = "id")
    private UserLibraryProfileJpa profile;

    @ManyToOne
    @JoinColumn(name = "fk_user_library_library", referencedColumnName = "id")
    private LibraryJpa library;

    @ManyToOne
    @JoinColumn(name = "fk_user_library_user_account", referencedColumnName = "id")
    private UserAccountJpa userAccount;

    @OneToMany(mappedBy = "userLibrary")
    private List<BorrowingJpa> borrowings;

    @OneToMany(mappedBy = "usersLib", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserLibraryCourseLinkJpa> userLibraryCourseLinks;
}
