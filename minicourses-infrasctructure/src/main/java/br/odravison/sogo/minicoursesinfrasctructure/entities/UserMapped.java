package br.odravison.sogo.minicoursesinfrasctructure.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("default")
@Table(name = "user_account")
@SQLDelete(sql = "UPDATE user_account SET deleted = true WHERE id = ?")
@Where(clause = BaseMappedEntity.WHERE_DELETED_CLAUSE)
public class UserMapped extends BaseMappedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_account_seq")
    @SequenceGenerator(name="user_account_seq", allocationSize = 1, sequenceName = "user_account_seq")
    @Column(name = "id")
    protected Long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "email", unique = true, nullable = false)
    protected String email;

    @Column(name = "password")
    protected String password;

    @Column(name = "id_role")
    protected Long idRole;

    @Column(name = "last_login_time")
    protected Long lastLoginTime;

    @Column(name = "deleted")
    protected boolean deleted;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
