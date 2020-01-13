package br.odravison.sogo.minicoursesinfrasctructure.repositories.impl;

import br.odravison.sogo.minicoursesdomain.infrasctructure.ProfessorRepository;
import br.odravison.sogo.minicoursesdomain.presentation.professor.CreateProfessorRequest;
import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorResponse;
import br.odravison.sogo.minicoursesinfrasctructure.entities.ProfessorMapped;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate.ProfessorMappedRepository;
import br.odravison.sogo.minicoursesinfrasctructure.utils.MapperUtils;

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
    public ReadProfessorResponse create(CreateProfessorRequest request) {

        ProfessorMapped professorMapped = MapperUtils.mapTo(request, ProfessorMapped.class);
        professorMapped = this.professorMappedRepository.save(professorMapped);

        ReadProfessorResponse response = MapperUtils.mapTo(professorMapped, ReadProfessorResponse.class);

        return response;
    }

    @Override
    public Optional<ReadProfessorResponse> read(Long id) {
        Optional<ReadProfessorResponse> response = Optional.empty();

        Optional<ProfessorMapped> mappedOptional = this.professorMappedRepository.findById(id);
        if (mappedOptional.isPresent()){
            ProfessorMapped mappedProfessor = mappedOptional.get();
            ReadProfessorResponse professorResponse = MapperUtils.mapTo(mappedProfessor, ReadProfessorResponse.class);
            response = Optional.of(professorResponse);
        }

        return response;
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.professorMappedRepository.existsByEmailAndDeletedFalse(email);
    }

    @Override
    public boolean existsByRegistration(String registration) {
        return this.professorMappedRepository.existsByRegistrationAndDeletedFalse(registration);
    }
}
