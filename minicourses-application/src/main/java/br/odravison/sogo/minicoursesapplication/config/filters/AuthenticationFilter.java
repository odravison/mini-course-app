package br.odravison.sogo.minicoursesapplication.config.filters;

import br.odravison.sogo.minicoursesapplication.config.AppContext;
import br.odravison.sogo.minicoursesapplication.config.dto.ErrorResponse;
import br.odravison.sogo.minicoursesapplication.config.security.authentication.TokenAuthenticationService;
import br.odravison.sogo.minicoursesapplication.config.security.authentication.exception.MultipleTokenGeneratedException;
import br.odravison.sogo.minicoursesapplication.utils.JSONUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        try {

            /* Checks the Authorization */
            Authentication auth = getTokenService().getAuthentication(request);

            SecurityContextHolder.getContext().setAuthentication(auth);

            chain.doFilter(request, response);
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } catch (MultipleTokenGeneratedException mte) {
            ErrorResponse errorResponse = new ErrorResponse(
                    System.currentTimeMillis(),
                    HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                    mte.getMessage(),
                    null);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getOutputStream().write(JSONUtil.toJSon(errorResponse).getBytes());
        }
    }

    private TokenAuthenticationService getTokenService() {
        return AppContext.getBean(TokenAuthenticationService.class);
    }

}
