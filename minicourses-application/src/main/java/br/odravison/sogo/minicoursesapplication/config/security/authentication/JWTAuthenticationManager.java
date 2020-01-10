package br.odravison.sogo.minicoursesapplication.config.security.authentication;

import br.odravison.sogo.minicoursesapplication.config.AppContext;
import br.odravison.sogo.minicoursesapplication.config.dto.LoggedUserDTO;
import br.odravison.sogo.minicoursesapplication.utils.CryptoUtil;
import br.odravison.sogo.minicoursesdomain.api.PermissionAPI;
import br.odravison.sogo.minicoursesdomain.api.UserAPI;
import br.odravison.sogo.minicoursesdomain.presentation.user.LoginUserResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;

public class JWTAuthenticationManager implements AuthenticationManager {

    private UserAPI userAPI;
    private PermissionAPI permissionAPI;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        LoginUserResponse loggedUser = this.getUserAPI().login(auth.getName(), CryptoUtil.hash((String) auth.getCredentials()));

        if (loggedUser != null) {
            return new UsernamePasswordAuthenticationToken(toUserDTO(loggedUser), auth.getCredentials());
        }

        throw new BadCredentialsException("Invalid username and/or password.");
    }

    /**
     * Add user's permissions to the DTO.
     */
    public LoggedUserDTO addPermissions(LoggedUserDTO dto, Long idRole) {
        dto.setPermissions(new ArrayList<>(getPermissionAPI().findPermissionByRole(idRole)));
        return dto;
    }

    /**
     * Gets the User API.
     */
    protected UserAPI getUserAPI() {

        if (this.userAPI == null) {
            this.userAPI = AppContext.getBean(UserAPI.class);
        }

        return this.userAPI;
    }

    /**
     * Gets the Permission API.
     */
    protected PermissionAPI getPermissionAPI() {

        if (this.permissionAPI == null) {
            this.permissionAPI = AppContext.getBean(PermissionAPI.class);
        }

        return this.permissionAPI;
    }

    /**
     * Converts the Logged User to DTO.
     *
     * @param user
     *            Logged User.
     * @return DTO.
     */
    private LoggedUserDTO toUserDTO(LoginUserResponse user) {

        LoggedUserDTO userDTO = new LoggedUserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setIdRole(user.getIdRole());
        this.addPermissions(userDTO, user.getIdRole());

        return userDTO;
    }
}
