package br.odravison.sogo.minicoursesapplication.ui.rest.professor.responses;

import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorsResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReadProfessorsResponseJSON {

    private List<SimpleProfessorJSONResponse> professors;

    public ReadProfessorsResponseJSON(ReadProfessorsResponse readProfessorsResponse) {
        this.professors = readProfessorsResponse.getProfessors().stream().map(professor -> {
           return new SimpleProfessorJSONResponse(professor.getId(), professor.getName(), professor.getEmail(), professor.getRegistration());
        }).collect(Collectors.toList());
    }

    public List<SimpleProfessorJSONResponse> getProfessors() {
        return professors;
    }
}
