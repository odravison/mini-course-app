package br.odravison.sogo.minicoursesdomain.domain;

import br.odravison.sogo.minicoursesdomain.presentation.user.LoginUserResponse;
import br.odravison.sogo.minicoursesdomain.presentation.user.UserResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Object used to group all equals properties between professor and student (participant)
 */

@Getter
@Setter
@NoArgsConstructor
public class User {

    protected Long id;

    protected String name;

    protected String email;

    protected String password;

    protected Long idRole;

    protected Long lastLoginTime;

    protected Boolean deleted;
}
