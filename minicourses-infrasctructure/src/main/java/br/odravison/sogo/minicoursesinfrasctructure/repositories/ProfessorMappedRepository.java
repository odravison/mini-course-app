package br.odravison.sogo.minicoursesinfrasctructure.repositories;

import br.odravison.sogo.minicoursesinfrasctructure.entities.ProfessorMapped;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorMappedRepository extends CrudRepository<ProfessorMapped, Long> {
}
