package br.odravison.sogo.minicoursesapplication.config.filters;

import br.odravison.sogo.minicoursesapplication.config.AppContext;
import br.odravison.sogo.minicoursesapplication.config.dto.ErrorResponse;
import br.odravison.sogo.minicoursesapplication.config.dto.LoggedUserDTO;
import br.odravison.sogo.minicoursesapplication.config.security.SecurityUtils;
import br.odravison.sogo.minicoursesapplication.config.security.authentication.AccountCredentials;
import br.odravison.sogo.minicoursesapplication.config.security.authentication.JWTAuthenticationManager;
import br.odravison.sogo.minicoursesapplication.config.security.authentication.TokenAuthenticationService;
import br.odravison.sogo.minicoursesapplication.utils.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    public LoginFilter(String url) {
        super(new AntPathRequestMatcher(url));

        this.setAuthenticationManager(new JWTAuthenticationManager());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        SecurityUtils.fillAccessControlHeader(response);

        AccountCredentials credentials = new ObjectMapper().readValue(request.getInputStream(), AccountCredentials.class);

        try {
            return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(),
                    credentials.getPassword(), Collections.emptyList()));
        } catch (BadCredentialsException e) {
            /**
             * If authentication has got an exception.
             * There's no need to execute the nested filters
             */
            ErrorResponse errorResponse = new ErrorResponse(
                    System.currentTimeMillis(),
                    HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                    e.getMessage(),
                    "/login");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getOutputStream().write(JSONUtil.toJSon(errorResponse).getBytes());
        }

        return null;

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException {

        LoggedUserDTO dto = (LoggedUserDTO) auth.getPrincipal();

        dto = getTokenService().addAuthentication(dto);

        response.getWriter().write(JSONUtil.toJSon(dto));
    }

    /**
     * Gets the token service.
     *
     * @return Token Service.
     */
    private TokenAuthenticationService getTokenService() {
        return AppContext.getBean(TokenAuthenticationService.class);
    }
}
