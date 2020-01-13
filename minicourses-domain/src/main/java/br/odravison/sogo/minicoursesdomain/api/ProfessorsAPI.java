package br.odravison.sogo.minicoursesdomain.api;

import br.odravison.sogo.minicoursesdomain.domain.validators.ValidationError;
import br.odravison.sogo.minicoursesdomain.infrasctructure.ProfessorRepository;
import br.odravison.sogo.minicoursesdomain.infrasctructure.UserRepository;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseDomainException;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseError;
import br.odravison.sogo.minicoursesdomain.presentation.professor.CreateProfessorRequest;
import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorResponse;
import br.odravison.sogo.minicoursesdomain.presentation.professor.ReadProfessorsResponse;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class ProfessorsAPI {

    private ProfessorRepository professorRepository;
    private UserRepository userRepository;

    public ProfessorsAPI(ProfessorRepository professorRepository,
                         UserRepository userRepository) {
        this.professorRepository = professorRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ReadProfessorsResponse readProfessors() {
        return new ReadProfessorsResponse(this.professorRepository.readProfessors());
    }

    @Transactional
    public ReadProfessorResponse createProfessor(CreateProfessorRequest createProfessorRequest) throws MiniCourseDomainException {
        validateProfessorCreation(createProfessorRequest);
        return this.professorRepository.create(createProfessorRequest);
    }

    private void validateProfessorCreation(CreateProfessorRequest  createProfessorRequest) throws MiniCourseDomainException {

        List<MiniCourseError> errors = new ArrayList<>();

        /*
         * Check if there is one user with the same email
         */
        if (this.userRepository.existsByEmail(createProfessorRequest.getEmail())) {
            errors.add(
                    new MiniCourseError(
                            ValidationError.EMAIL_ALREADY_IN_USE.code,
                            "email",
                            ValidationError.EMAIL_ALREADY_IN_USE.message
                    )
            );
        }

        /*
         * Check if there is one professor with the same registration
         */

        if (this.professorRepository.existsByRegistration(createProfessorRequest.getRegistration())) {
            errors.add(
                    new MiniCourseError(
                            ValidationError.REGISTRATION_ALREADY_IN_USE.code,
                            "registration",
                            ValidationError.REGISTRATION_ALREADY_IN_USE.message
                    )
            );
        }

        if (!errors.isEmpty()) {
            throw new MiniCourseDomainException(errors);
        }

    }



}
