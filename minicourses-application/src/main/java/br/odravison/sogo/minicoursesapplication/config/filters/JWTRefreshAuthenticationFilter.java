package br.odravison.sogo.minicoursesapplication.config.filters;

import br.odravison.sogo.minicoursesapplication.config.AppContext;
import br.odravison.sogo.minicoursesapplication.config.dto.LoggedUserDTO;
import br.odravison.sogo.minicoursesapplication.config.security.SecurityUtils;
import br.odravison.sogo.minicoursesapplication.config.security.authentication.JWTAuthenticationManager;
import br.odravison.sogo.minicoursesapplication.config.security.authentication.TokenAuthenticationService;
import br.odravison.sogo.minicoursesapplication.utils.JSONUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTRefreshAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JWTRefreshAuthenticationFilter(String url) {
        super(new AntPathRequestMatcher(url));

        this.setAuthenticationManager(new JWTAuthenticationManager());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {

        response = SecurityUtils.fillAccessControlHeader(response);

        return getTokenService().getRefreshAuthentication(request);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        LoggedUserDTO dto = getTokenService().addAuthentication((LoggedUserDTO) auth.getPrincipal());
        dto = getManager().addPermissions(dto, dto.getIdRole());

        response.setHeader("Content-Type", "application/json");
        response.getWriter().write(JSONUtil.toJSon(dto));
    }

    private TokenAuthenticationService getTokenService() {
        return AppContext.getBean(TokenAuthenticationService.class);
    }

    private JWTAuthenticationManager getManager() {
        return (JWTAuthenticationManager) getAuthenticationManager();
    }

}
