package br.odravison.sogo.minicoursesapplication;


import br.odravison.sogo.minicoursesdomain.api.PermissionAPI;
import br.odravison.sogo.minicoursesdomain.api.ProfessorsAPI;
import br.odravison.sogo.minicoursesdomain.api.UserAPI;
import br.odravison.sogo.minicoursesdomain.infrasctructure.PermissionReposistory;
import br.odravison.sogo.minicoursesdomain.infrasctructure.ProfessorRepository;
import br.odravison.sogo.minicoursesdomain.infrasctructure.UserRepository;
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

    @Bean
    public PermissionAPI permissionAPI(PermissionReposistory permissionReposistory) {
        return new PermissionAPI(permissionReposistory);
    }

    @Bean
    public UserAPI userAPI(UserRepository userRepository) {
        return new UserAPI(userRepository);
    }

}
