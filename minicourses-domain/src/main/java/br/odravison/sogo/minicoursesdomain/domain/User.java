package br.odravison.sogo.minicoursesdomain.domain;

import lombok.*;


/**
 * Object used to group all equals properties between professor and student (participant)
 */

@Getter
@Setter
public class User {

    protected Long id;

    protected String name;

    protected String email;

    protected String password;



}
