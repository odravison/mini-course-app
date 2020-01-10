package br.odravison.sogo.minicoursesdomain.domain;

import br.odravison.sogo.minicoursesdomain.presentation.user.LoginUserResponse;
import br.odravison.sogo.minicoursesdomain.presentation.user.UserResponse;
import lombok.Getter;
import lombok.Setter;


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

    protected Long idRole;

    protected Long lastLoginTime;

    public LoginUserResponse buildLoginUserResponse(){
        return new LoginUserResponse(this.id, this.name, this.email, this.idRole, this.lastLoginTime);
    }

    public static LoginUserResponse buildLoginUserResponse(User user){
        return new LoginUserResponse(user.getId(), user.getName(), user.getEmail(), user.getIdRole(), user.getLastLoginTime());
    }

    public UserResponse buildUserResponse(){
        return new UserResponse(this.id, this.name, this.email, this.idRole, this.lastLoginTime);
    }

    public static UserResponse buildUserResponse(User user){
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getIdRole(), user.getLastLoginTime());
    }

}
