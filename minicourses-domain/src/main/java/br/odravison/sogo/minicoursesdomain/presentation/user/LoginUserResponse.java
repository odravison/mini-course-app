package br.odravison.sogo.minicoursesdomain.presentation.user;

import br.odravison.sogo.minicoursesdomain.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginUserResponse {

    private Long id;

    private String name;

    private String email;

    private Long idRole;

    private Long lastLoginTime;

    public LoginUserResponse(User user) {
        if (user != null){
            this.id = user.getId();
            this.idRole =  user.getIdRole();
            this.name = user.getName();
            this.email = user.getEmail();
            this.lastLoginTime = user.getLastLoginTime();
        }
    }

}
