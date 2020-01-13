package br.odravison.sogo.minicoursesapplication.ui.rest.student.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReadStudentJSONResponse {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Date birthday;
    private String cpf;

}
