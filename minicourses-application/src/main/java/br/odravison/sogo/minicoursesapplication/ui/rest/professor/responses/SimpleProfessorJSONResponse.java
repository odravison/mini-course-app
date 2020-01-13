package br.odravison.sogo.minicoursesapplication.ui.rest.professor.responses;

import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorsResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class SimpleProfessorJSONResponse {

    private Long id;

    private String name;

    private String email;

    private String registration;

    public SimpleProfessorJSONResponse(Long id, String name, String email, String registration) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registration = registration;
    }

    public SimpleProfessorJSONResponse(String name, String registration) {
        this.name = name;
        this.registration = registration;
    }
}
