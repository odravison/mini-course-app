package br.odravison.sogo.minicoursesapplication;


import br.odravison.sogo.minicoursesdomain.api.*;
import br.odravison.sogo.minicoursesdomain.infrasctructure.*;
import br.odravison.sogo.minicoursesinfrasctructure.InfrastructureConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({InfrastructureConfiguration.class})
public class MiniCoursesConfiguration {

    @Bean
    public ProfessorsAPI professorsAPI(ProfessorRepository professorRepository,
                                       UserRepository userRepository) {
        return new ProfessorsAPI(professorRepository, userRepository);
    }

    @Bean
    public PermissionAPI permissionAPI(PermissionReposistory permissionReposistory) {
        return new PermissionAPI(permissionReposistory);
    }

    @Bean
    public UserAPI userAPI(UserRepository userRepository) {
        return new UserAPI(userRepository);
    }

    @Bean
    public MiniCourseAPI miniCourseAPI(MiniCourseRepository miniCourseRepository) {
        return new MiniCourseAPI(miniCourseRepository);
    }

    @Bean
    public StudentsAPI studentsAPI(StudentRepository studentRepository,
                                   UserRepository userRepository) {
        return new StudentsAPI(studentRepository, userRepository);
    }

}
