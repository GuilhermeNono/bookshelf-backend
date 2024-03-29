package br.com.projlib.bookshelf.infra.gateway.usercontacttype;

import br.com.projlib.bookshelf.infra.gateway.usercontact.UserContactJpa;
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
@Table(name = "user_contact_type")
@Getter
@Setter
public class UserContactTypeJpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String description;

    @OneToMany(mappedBy = "userContactType",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<UserContactJpa> contact;
}
