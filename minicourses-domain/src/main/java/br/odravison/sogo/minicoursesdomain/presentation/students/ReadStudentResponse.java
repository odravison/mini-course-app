package br.odravison.sogo.minicoursesdomain.presentation.students;

import java.util.Date;

public class ReadStudentResponse {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Date birthday;

    private String cpf;

    public ReadStudentResponse(Long id, String name, String email, String password, Date birthday, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.cpf = cpf;
    }

    public ReadStudentResponse(String name, String email, String password, Date birthday, String cpf) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.cpf = cpf;
    }


}
