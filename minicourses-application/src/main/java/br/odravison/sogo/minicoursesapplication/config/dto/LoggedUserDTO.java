package br.odravison.sogo.minicoursesapplication.config.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoggedUserDTO {

    private Long id;
    private String name;
    private String email;
    private Long idRole;
    private String token;
    private String refreshToken;
    private List<String> permissions;

    public LoggedUserDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public LoggedUserDTO(Long id, String email, Long idRole) {
        this.id = id;
        this.email = email;
        this.idRole = idRole;
    }
}
