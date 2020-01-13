package br.odravison.sogo.minicoursesapplication.utils;

import br.odravison.sogo.minicoursesapplication.config.dto.LoggedUserDTO;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static LoggedUserDTO getCurrentUser() {
        return (LoggedUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
