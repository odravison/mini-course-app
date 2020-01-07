package br.odravison.sogo.minicoursesdomain.api;

import br.odravison.sogo.minicoursesdomain.infrasctructure.ProfessorRepository;
import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorsResponse;

import javax.transaction.Transactional;

public class ProfessorsAPI {

    private ProfessorRepository professorRepository;

    public ProfessorsAPI(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Transactional
    public ReadProfessorsResponse readProfessors() {
        return new ReadProfessorsResponse(this.professorRepository.readProfessors());
    }



}
