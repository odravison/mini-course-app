package br.odravison.sogo.minicoursesdomain.presentation.professor;

import br.odravison.sogo.minicoursesdomain.domain.Professor;
import lombok.Getter;

import java.util.List;

@Getter
public class ReadProfessorsResponse {

    private List<ReadProfessorResponse> professors;

    public ReadProfessorsResponse(List<ReadProfessorResponse> professors) {
        this.professors = professors;
    }

}
