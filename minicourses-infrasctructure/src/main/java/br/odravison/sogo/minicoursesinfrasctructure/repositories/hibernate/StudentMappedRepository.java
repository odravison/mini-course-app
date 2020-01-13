package br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate;

import br.odravison.sogo.minicoursesinfrasctructure.entities.StudentMapped;
import org.springframework.data.repository.CrudRepository;

public interface StudentMappedRepository extends CrudRepository<StudentMapped, Long> {

    boolean existsByEmailIgnoreCaseAndDeletedFalse(String email);

    boolean existsByCpfAndDeletedFalse(String cpf);
}
