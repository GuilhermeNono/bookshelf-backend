package br.com.projlib.bookshelf.infra.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "user-accounts", itemRelation = "user-account")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class UserAccountQuery extends RepresentationModel<UserAccountQuery> {

    private long id;
    private String cpf;
    private long profileId;
    private String profile;
    private boolean active;
    private String personName;
    private UserAccountQuery() {
        super();
    }

    public UserAccountQuery(String cpf, String profile, boolean active, long profileId) {
        this();
        this.setProfile(profile);
        this.setCpf(cpf);
        this.setActive(active);
        this.setProfileId(profileId);
    }

}
