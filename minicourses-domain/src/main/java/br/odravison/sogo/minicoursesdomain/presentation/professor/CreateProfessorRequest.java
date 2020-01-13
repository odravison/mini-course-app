package br.odravison.sogo.minicoursesdomain.presentation.professor;

import br.odravison.sogo.minicoursesdomain.domain.Professor;
import br.odravison.sogo.minicoursesdomain.domain.validators.CreateProfessorValidator;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseDomainException;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseError;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CreateProfessorRequest {

    private static final String NAME_FIELD = "name";
    private static final String EMAIL_FIELD = "email";
    private static final String PASSWORD_FIELD = "password";
    private static final String ID_ROLE_FIELD = "idRole";
    private static final String REGISTRATION_FIELD = "registration";
    private static final String PHONES_FIELD = "phones";

    private String name;

    private String email;

    private String password;

    private Long idRole;

    private String registration;

    private List<String> phones;

    private CreateProfessorRequest(String name,
                                   String email,
                                   String password,
                                   Long idRole,
                                   String registration,
                                   List<String> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.idRole = idRole;
        this.registration = registration;
        this.phones = phones;
    }

    public static CreateProfessorRequest createCreateProfessorRequest(
            String name, String email, String password,String registration,
             List<String> phones) throws MiniCourseDomainException {

        validate(name, email, password, registration, phones);
        CreateProfessorRequest request = new CreateProfessorRequest(name, email, password,
                Professor.PROFESSOR_ROLE_ID, registration, phones);
        return request;
    }

    public static void validate(
            String name, String email, String password, String registration,
            List<String> phones) throws MiniCourseDomainException {

        List<MiniCourseError> errors = new ArrayList<>();

        errors.addAll(CreateProfessorValidator.validate(name, email, password, registration, phones));

        if (!errors.isEmpty()){
            MiniCourseDomainException exception = new MiniCourseDomainException(errors);
            throw exception;
        }
    }
}
