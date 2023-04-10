package br.com.projlib.bookshelf.infra.gateway.usercontact;

import br.com.projlib.bookshelf.core.domain.Contact;
import br.com.projlib.bookshelf.infra.gateway.useraccountjpa.UserAccountJpa;
import br.com.projlib.bookshelf.infra.gateway.usercontacttype.UserContactTypeJpa;
import jakarta.persistence.CascadeType;
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
@Table(name = "user_contact")
@Getter
@Setter
public class UserContactJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean active;

    @Column
    private String contact;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "fk_user_contact_user_account", referencedColumnName = "id")
    private UserAccountJpa userAccount;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "fk_user_contact_user_contact_type", referencedColumnName = "id")
    private UserContactTypeJpa userContactType;

    public Contact toDomain() {
        return new Contact(
                this.getId(),
                this.isActive(),
                this.getContact(),
                this.getCreatedAt(),
                this.getUpdatedAt(),
                this.getUserContactType().getDescription()
        );
    }

    public static UserContactJpa fromDomain(Contact userContact) {
        UserContactJpa userContactJpa = new UserContactJpa();

        userContactJpa.setContact(userContact.getContact());
        userContactJpa.setId(userContact.getId());
        userContactJpa.setActive(userContact.isActive());
        userContactJpa.setCreatedAt(userContact.getCreatedAt());
        userContactJpa.setUpdatedAt(userContact.getUpdatedAt());

        return userContactJpa;
    }
}
