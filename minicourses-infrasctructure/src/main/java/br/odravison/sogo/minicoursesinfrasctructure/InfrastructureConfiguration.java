package br.odravison.sogo.minicoursesinfrasctructure;

import br.odravison.sogo.minicoursesdomain.infrasctructure.*;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate.*;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.impl.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("br.odravison.sogo.minicoursesinfrasctructure.repositories")
@EntityScan("br.odravison.sogo.minicoursesinfrasctructure.entities")
public class InfrastructureConfiguration {

    @Bean
    public ProfessorRepository professorRepository (ProfessorMappedRepository professorMappedRepository) {
        return new ProfessorRepositoryImpl(professorMappedRepository);
    }

    @Bean
    public PermissionReposistory permissionRepository (PermissionMappedRepository permissionMappedRepository) {
        return new PermissionRepositoryImpl(permissionMappedRepository);
    }

    @Bean
    public UserRepository userRepository (UserMappedRepository userMappedRepository) {
        return new UserRepositoryImpl(userMappedRepository);
    }

    @Bean
    public MiniCourseRepository miniCourseRepository (MiniCourseMappedRepository miniCourseMappedRepository,
                                                      ProfessorMappedRepository professorRepository) {
        return new MiniCourseRepositoryImpl(miniCourseMappedRepository, professorRepository);
    }

    @Bean
    public StudentRepository studentRepository (StudentMappedRepository studentMappedRepository) {
        return new StudentRepositoryImpl(studentMappedRepository);
    }

}
