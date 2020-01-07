package br.odravison.sogo.minicoursesinfrasctructure;

import br.odravison.sogo.minicoursesdomain.infrasctructure.ProfessorRepository;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.ProfessorMappedRepository;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.ProfessorRepositoryImpl;
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

}
