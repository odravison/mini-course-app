package br.odravison.sogo.minicoursesapplication.config.security.authentication;

import br.odravison.sogo.minicoursesapplication.config.AppContext;
import br.odravison.sogo.minicoursesapplication.config.dto.LoggedUserDTO;
import br.odravison.sogo.minicoursesapplication.config.dto.TokenDTO;
import br.odravison.sogo.minicoursesapplication.config.security.authentication.exception.MultipleTokenGeneratedException;
import br.odravison.sogo.minicoursesdomain.api.UserAPI;
import br.odravison.sogo.minicoursesdomain.presentation.user.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

import static java.util.Collections.emptyList;

@Service
public class TokenAuthenticationService {

    private UserAPI userAPI;

    public static final String HEADER = "Authorization";

    protected static final String CLAIM_ROLE = "role";

    protected static final String CLAIM_TIME_ISSUED = "time-issued";

    @Value("${app.token.secretkey}")
    private String SECRET_KEY;

    /**
     * Token Expiration.
     */
    @Value("${app.token.expiration}")
    private long EXPIRATION_TOKEN;

    /**
     * Refresh Token Expiration.
     */
    @Value("${app.refreshtoken.expiration}")
    private long EXPIRATION_REFRESH_TOKEN;

    /**
     * Adds the authentication in the response.
     */
    public LoggedUserDTO addAuthentication(LoggedUserDTO dto) {

        Long lastLoginTime = System.currentTimeMillis();

        String jwt = this.generateToken(lastLoginTime, dto, EXPIRATION_TOKEN);
        String refresh = this.generateToken(lastLoginTime, dto, EXPIRATION_REFRESH_TOKEN);

        dto.setToken(jwt);
        dto.setRefreshToken(refresh);

        this.getUserAPI().updateLastLoginTime(dto.getId(), lastLoginTime);

        return dto;
    }

    /**
     * Gets the authentication.
     */
    public Authentication getAuthentication(HttpServletRequest request) throws MultipleTokenGeneratedException, IOException {

        String token = request.getHeader(HEADER);

        if (token != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token).getBody();

            if (claims != null) {
                return this.processTokenClaims(claims);
            }
        }

        return null;
    }

    /**
     * Generate Token.
     */
    private String generateToken(Long lastLoginTime, LoggedUserDTO dto, long expirationTokenTime) {

        return Jwts.builder().setId(String.valueOf(dto.getId())).setSubject(dto.getEmail())
                .claim(CLAIM_ROLE, dto.getIdRole())
                .claim(CLAIM_TIME_ISSUED, lastLoginTime)
                .setExpiration(new Date(lastLoginTime + expirationTokenTime))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    /**
     * Gets the refresh authentication.
     */
    public Authentication getRefreshAuthentication(HttpServletRequest request) throws MultipleTokenGeneratedException {

        String refreshToken;
        try {
            refreshToken = new ObjectMapper().readValue(request.getInputStream(), TokenDTO.class).getToken();
        } catch (IOException e) {
            return null;
        }

        if (refreshToken != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(refreshToken).getBody();

            if (claims != null) {
                return this.processTokenClaims(claims);
            }

        }

        return null;
    }

    private Authentication processTokenClaims(Claims claims) throws MultipleTokenGeneratedException {
        Long lastLoginTime = Long.valueOf(claims.get(CLAIM_TIME_ISSUED).toString());

        /*
         * Check if it's the last token generated, if not must re-login;
         */
        UserResponse userResponse = this.getUserAPI().findByEmailAndDeleted(claims.getSubject(), false);
        this.checkIfIsLastTokenGenerated(userResponse, lastLoginTime);

        Long idRole = Long.valueOf(claims.get(CLAIM_ROLE).toString());
        LoggedUserDTO userDTO = new LoggedUserDTO(Long.valueOf(claims.getId()),
                claims.getSubject(),
                idRole);
        return new UsernamePasswordAuthenticationToken(userDTO, null, emptyList());
    }

    private void checkIfIsLastTokenGenerated(UserResponse userResponse, Long lastLoginTimeClaim) throws MultipleTokenGeneratedException {
        if (userResponse != null && userResponse.getLastLoginTime() != null) {
            if (userResponse.getLastLoginTime() > lastLoginTimeClaim) {
                throw new MultipleTokenGeneratedException("Session expired / Invalid token");
            }
        }
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

}
