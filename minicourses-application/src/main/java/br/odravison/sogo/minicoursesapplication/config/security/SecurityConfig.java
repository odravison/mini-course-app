package br.odravison.sogo.minicoursesapplication.config.security;

import br.odravison.sogo.minicoursesapplication.config.filters.AuthenticationFilter;
import br.odravison.sogo.minicoursesapplication.config.filters.CORSOptionsFilter;
import br.odravison.sogo.minicoursesapplication.config.filters.JWTRefreshAuthenticationFilter;
import br.odravison.sogo.minicoursesapplication.config.filters.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                // Login
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/students").permitAll()
                .antMatchers(HttpMethod.POST, "/refresh").permitAll()

                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new CORSOptionsFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new LoginFilter("/login"), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTRefreshAuthenticationFilter("/refresh"), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
