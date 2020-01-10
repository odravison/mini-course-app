package br.odravison.sogo.minicoursesinfrasctructure;

import br.odravison.sogo.minicoursesdomain.infrasctructure.PermissionReposistory;
import br.odravison.sogo.minicoursesdomain.infrasctructure.ProfessorRepository;
import br.odravison.sogo.minicoursesdomain.infrasctructure.UserRepository;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate.UserMappedRepository;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.impl.PermissionRepositoryImpl;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate.PermissionMappedRepository;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate.ProfessorMappedRepository;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.impl.ProfessorRepositoryImpl;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.impl.UserRepositoryImpl;
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

}
