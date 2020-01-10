package br.odravison.sogo.minicoursesapplication;

import br.odravison.sogo.minicoursesapplication.config.AppContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableCaching
@EnableWebMvc
public class MinicoursesApplication {

    /**
     * Main method.
     *
     * Custom method to wrapper the spring application context and get an own application context.
     * @param args
     */
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(MinicoursesApplication.class, args);
        AppContext.loadApplicationContext(ctx);
    }

}
