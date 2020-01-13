package br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate;

import br.odravison.sogo.minicoursesinfrasctructure.entities.MiniCourseMapped;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiniCourseMappedRepository extends CrudRepository<MiniCourseMapped, Long> {

}
