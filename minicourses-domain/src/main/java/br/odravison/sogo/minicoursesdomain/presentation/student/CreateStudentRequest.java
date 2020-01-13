package br.odravison.sogo.minicoursesdomain.presentation.student;

import br.odravison.sogo.minicoursesdomain.domain.Student;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseDomainException;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseError;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class CreateStudentRequest {

    private String name, password, email, cpf;

    private Long idRole;

    private Date birthday;

    public static CreateStudentRequest createStudentRequest(String name, String password, String email, String cpf, Date birthday) throws MiniCourseDomainException {
        CreateStudentRequest request = new CreateStudentRequest();
        validate(name, password, email, cpf, birthday);

        request.name = name;
        request.password = password;
        request.email = email;
        request.cpf = cpf;
        request.idRole = Student.STUDENT_ROLE_ID;
        request.birthday = birthday;

        return request;
    }

    private static void validate(String name, String password, String email, String cpf, Date birthday) throws MiniCourseDomainException {

        List<MiniCourseError> errors = new ArrayList<>();

        /**
         * For STUDENTS I'll use another approach for fields validation
         * Using @Valid annotation in requestBody parameter and
         * Using @NotNull annotation on DTO parameters that supposed not be null
         */


        if (!errors.isEmpty()) {
            throw new MiniCourseDomainException(errors);
        }

    }


}
