package br.odravison.sogo.minicoursesapplication.ui.rest.professor.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorCreationJSONRequest {

    private String name;

    private String email;

    private String password;

    private String registration;

    private List<String> phones;

}
