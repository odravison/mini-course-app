package br.odravison.sogo.minicoursesapplication.ui.rest.professor.responses;

import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorsResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReadProfessorsResponseJSON {

    private List<SimpleProfessorJsonResponse> professors;

    public ReadProfessorsResponseJSON(ReadProfessorsResponse readProfessorsResponse) {
        this.professors = readProfessorsResponse.getProfessors().stream().map(professor -> {
           return new SimpleProfessorJsonResponse(professor.getId(), professor.getName(), professor.getRegistration());
        }).collect(Collectors.toList());
    }

    public List<SimpleProfessorJsonResponse> getProfessors() {
        return professors;
    }
}
