package br.odravison.sogo.minicoursesinfrasctructure.entities;

import br.odravison.sogo.minicoursesdomain.presentation.student.CreateStudentRequest;
import br.odravison.sogo.minicoursesdomain.presentation.student.ReadStudentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("STUDENT")
@SQLDelete(sql = "UPDATE user_account SET deleted = true WHERE id = ?")
@Where(clause = BaseMappedEntity.WHERE_DELETED_CLAUSE)
public class StudentMapped extends UserMapped {

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "cpf", unique = true)
    private String cpf;

    public StudentMapped(Long id, String name, String email, String password, Date birthday, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.cpf = cpf;
    }

    public StudentMapped(String name, String email, String password, Date birthday, String cpf) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.cpf = cpf;
    }

    public ReadStudentResponse buildReadStudentResponse() {
        return new ReadStudentResponse(this.id, this.name, this.email, this.password, this.birthday, this.cpf);
    }

    public static ReadStudentResponse buildReadStudentResponse(StudentMapped studentMapped) {
        return new ReadStudentResponse(
                studentMapped.getId(),
                studentMapped.getName(),
                studentMapped.getEmail(),
                studentMapped.getPassword(),
                studentMapped.getBirthday(),
                studentMapped.getCpf()
        );
    }

    public static StudentMapped buildStudentMappedFromCreateRequest(CreateStudentRequest studentRequest) {
        return new StudentMapped(
                studentRequest.getName(),
                studentRequest.getEmail(),
                studentRequest.getPassword(),
                studentRequest.getBirthday(),
                studentRequest.getCpf()
        );
    }

}
