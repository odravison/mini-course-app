package br.odravison.sogo.minicoursesapplication.ui.rest.professor.responses;

import lombok.Getter;

@Getter
public class SimpleProfessorJsonResponse {

    private Long id;

    private String name;

    private String registration;

    public SimpleProfessorJsonResponse(Long id, String name, String registration) {
        this.id = id;
        this.name = name;
        this.registration = registration;
    }

    public SimpleProfessorJsonResponse(String name, String registration) {
        this.name = name;
        this.registration = registration;
    }
}
