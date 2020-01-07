package br.odravison.sogo.minicoursesdomain.presentation.professor;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReadProfessorResponse {

    private Long id;

    private String name;

    private String email;

    private String password;

    private String registration;

    private List<String> phones;

    public ReadProfessorResponse(Long id, String name, String email, String password, String registration, List<String> phones) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.phones = phones;
    }

    public ReadProfessorResponse(String name, String email, String password, String registration, List<String> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.phones = phones;
    }
}
