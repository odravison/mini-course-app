package br.odravison.sogo.minicoursesapplication.ui.rest.student.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CreateStudentJSONRequest {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Date birthday;

    @NotNull
    private String cpf;

}
