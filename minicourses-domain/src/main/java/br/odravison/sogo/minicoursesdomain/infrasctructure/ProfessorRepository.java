package br.odravison.sogo.minicoursesdomain.infrasctructure;

import br.odravison.sogo.minicoursesdomain.presentation.professor.CreateProfessorRequest;
import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorResponse;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository {

    List<ReadProfessorResponse> readProfessors();

    ReadProfessorResponse create(CreateProfessorRequest createProfessorRequest);

    Optional<ReadProfessorResponse> read(Long id);

    boolean existsByEmail(String email);

    boolean existsByRegistration(String registration);
}
