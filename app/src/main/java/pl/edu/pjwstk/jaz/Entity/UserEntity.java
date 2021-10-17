package pl.edu.pjwstk.jaz.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "passwordhash")
    private String passwordHash;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
    private List<AuthorityEntity> authorityEntity = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<AuthorityEntity> getAuthorityEntity() {
        return authorityEntity;
    }

    public void setAuthorityEntity(List<AuthorityEntity> authorityEntity) {
        this.authorityEntity = authorityEntity;
    }
}