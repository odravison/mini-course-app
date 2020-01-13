package br.odravison.sogo.minicoursesapplication.config.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private boolean isProfessor = false;
    @JsonIgnore
    private boolean isStudent = false;

    public LoggedUserDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public LoggedUserDTO(Long id, String email, Long idRole) {
        this.id = id;
        this.email = email;
        this.idRole = idRole;
        this.isProfessor = (idRole == 2L);
        this.isStudent = (idRole == 3L);
    }
}
