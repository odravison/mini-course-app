package br.odravison.sogo.minicoursesdomain.domain;


import lombok.*;

import java.util.Date;

@Getter
@Setter
public class Student extends User {

    private Date birthday;

    private String cpf;
}
