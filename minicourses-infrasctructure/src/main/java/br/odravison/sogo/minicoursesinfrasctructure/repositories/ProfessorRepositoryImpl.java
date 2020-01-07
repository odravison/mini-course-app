package br.odravison.sogo.minicoursesinfrasctructure.repositories;

import br.odravison.sogo.minicoursesdomain.domain.Professor;
import br.odravison.sogo.minicoursesdomain.infrasctructure.ProfessorRepository;
import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorResponse;
import br.odravison.sogo.minicoursesinfrasctructure.entities.ProfessorMapped;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProfessorRepositoryImpl implements ProfessorRepository {

    private ProfessorMappedRepository professorMappedRepository;

    public ProfessorRepositoryImpl(ProfessorMappedRepository professorMappedRepository) {
        this.professorMappedRepository = professorMappedRepository;
    }

    @Override
    public List<ReadProfessorResponse> readProfessors() {
        List<ReadProfessorResponse> professors = new LinkedList<>();

        this.professorMappedRepository.findAll().forEach(professorMapped -> {
            professors.add(professorMapped.buildReadProfessorResponse());
        });

        return professors;
    }

    @Override
    public Long create(Professor professor) {
        return null;
    }

    @Override
    public Optional<Professor> read(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Professor professor) {

    }

    @Override
    public void delete(Long id) {

    }
}
