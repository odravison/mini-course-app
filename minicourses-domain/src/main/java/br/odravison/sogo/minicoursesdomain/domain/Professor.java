package br.odravison.sogo.minicoursesdomain.domain;

import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Professor extends User {

    public static final Long PROFESSOR_ROLE_ID = 2L;
    private String registration;

    private List<String> phones;

    public ReadProfessorResponse buildReadProfessorResponse() {
        return new ReadProfessorResponse(this.name, this.email, this.password, this.registration, this.phones);
    }

}
