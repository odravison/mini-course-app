package br.odravison.sogo.minicoursesdomain.api;

import br.odravison.sogo.minicoursesdomain.domain.Student;
import br.odravison.sogo.minicoursesdomain.domain.validators.ValidationError;
import br.odravison.sogo.minicoursesdomain.infrasctructure.ProfessorRepository;
import br.odravison.sogo.minicoursesdomain.infrasctructure.StudentRepository;
import br.odravison.sogo.minicoursesdomain.infrasctructure.UserRepository;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseDomainException;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseError;
import br.odravison.sogo.minicoursesdomain.presentation.student.CreateStudentRequest;
import br.odravison.sogo.minicoursesdomain.presentation.student.ReadStudentResponse;
import br.odravison.sogo.minicoursesdomain.presentation.student.ReadStudentsResponse;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentsAPI {

    private StudentRepository studentRepository;
    private UserRepository userRepository;

    public StudentsAPI (StudentRepository studentRepository,
                        UserRepository userRepository){
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ReadStudentsResponse readStudents() {
        return new ReadStudentsResponse(
                this.studentRepository.readStudents()
                        .stream()
                        .map(Student::buildReadStudentResponse)
                        .collect(Collectors.toList())
        );
    }

    @Transactional
    public ReadStudentResponse studentSignUp(CreateStudentRequest createStudentRequest) throws MiniCourseDomainException {

        validateSignUp(createStudentRequest);

        Student student = Student.buildFromCreateRequest(createStudentRequest);
        student = this.studentRepository.create(student);
        return Student.buildReadStudentResponse(student);

    }

    private void validateSignUp(CreateStudentRequest createStudentRequest) throws MiniCourseDomainException {

        List<MiniCourseError> errors = new ArrayList<>();

        /*
         * Check if there is one student with the same email
         */
        if (this.userRepository.existsByEmail(createStudentRequest.getEmail())) {
            errors.add(
                new MiniCourseError(
                    ValidationError.EMAIL_ALREADY_IN_USE.code,
                    "EMAIL",
                    ValidationError.EMAIL_ALREADY_IN_USE.message
                )
            );
        }

        /*
         * Check if there is one student with the same CPF
         */

        if (this.studentRepository.existsByCpf(createStudentRequest.getCpf())) {
            errors.add(
                new MiniCourseError(
                        ValidationError.CPF_ALREADY_IN_USE.code,
                        "CPF",
                        ValidationError.CPF_ALREADY_IN_USE.message
                )
            );
        }

        if (!errors.isEmpty()) {
            throw new MiniCourseDomainException(errors);
        }
    }
}
