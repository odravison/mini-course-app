package br.odravison.sogo.minicoursesinfrasctructure.entities;

import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("PROFESSOR")
@SQLDelete(sql = "UPDATE user_account SET deleted = true WHERE id = ?")
@Where(clause = BaseMappedEntity.WHERE_DELETED_CLAUSE)
public class ProfessorMapped extends UserMapped {

    @Column(name = "registration", unique = true)
    private String registration;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name ="professor_phones")
    private List<String> phones;

    public static ReadProfessorResponse buildReadProfessorResponse(ProfessorMapped professor) {
        return new ReadProfessorResponse(professor.name, professor.email, professor.password, professor.registration, professor.phones);
    }

    public ReadProfessorResponse buildReadProfessorResponse() {
        return new ReadProfessorResponse(this.id, this.name, this.email, this.password, this.registration, this.phones);
    }

}
