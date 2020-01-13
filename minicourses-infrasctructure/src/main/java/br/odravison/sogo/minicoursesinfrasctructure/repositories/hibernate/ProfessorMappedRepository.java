package br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate;

import br.odravison.sogo.minicoursesinfrasctructure.entities.ProfessorMapped;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorMappedRepository extends CrudRepository<ProfessorMapped, Long> {

    boolean existsByEmailAndDeletedFalse(String email);

    boolean existsByRegistrationAndDeletedFalse(String registration);
}
