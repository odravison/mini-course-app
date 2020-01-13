package br.odravison.sogo.minicoursesdomain.domain;


import br.odravison.sogo.minicoursesdomain.presentation.student.CreateStudentRequest;
import br.odravison.sogo.minicoursesdomain.presentation.student.ReadStudentResponse;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Student extends User {

    public static final Long STUDENT_ROLE_ID = 3L;

    private Date birthday;

    private String cpf;

    public Student(Long id, String name, String email, String password, Date birthday, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.idRole = STUDENT_ROLE_ID;
        this.birthday = birthday;
        this.cpf = cpf;
    }

    public static ReadStudentResponse buildReadStudentResponse(Student student) {
        return new ReadStudentResponse(
                student.getId(),
                student.getName(),
                student.getEmail(),
                null,
                student.getBirthday(),
                student.getCpf()
        );
    }

    public static Student buildFromCreateRequest(CreateStudentRequest request){
        return new Student(null,
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getBirthday(),
                request.getCpf());
    }


}
