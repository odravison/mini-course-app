package br.odravison.sogo.minicoursesapplication;


import br.odravison.sogo.minicoursesdomain.api.ProfessorsAPI;
import br.odravison.sogo.minicoursesdomain.infrasctructure.ProfessorRepository;
import br.odravison.sogo.minicoursesinfrasctructure.InfrastructureConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({InfrastructureConfiguration.class})
public class MinicoursesConfiguration {

    @Bean
    public ProfessorsAPI professorsAPI(ProfessorRepository professorRepository) {
        return new ProfessorsAPI(professorRepository);
    }

}
