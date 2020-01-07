package br.odravison.sogo.minicoursesdomain.infrasctructure;

import br.odravison.sogo.minicoursesdomain.domain.Professor;
import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorResponse;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {

    List<ReadProfessorResponse> readProfessors();

    Long create(Professor professor);

    Optional<Professor> read(Long id);

    void update(Professor professor);

    void delete(Long id);
}
