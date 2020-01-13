package br.odravison.sogo.minicoursesdomain.api;

import br.odravison.sogo.minicoursesdomain.domain.MiniCourse;
import br.odravison.sogo.minicoursesdomain.domain.Student;
import br.odravison.sogo.minicoursesdomain.domain.validators.ValidationError;
import br.odravison.sogo.minicoursesdomain.infrasctructure.MiniCourseRepository;
import br.odravison.sogo.minicoursesdomain.infrasctructure.StudentRepository;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseDomainException;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseError;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseNotFoundException;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.StudentNotFoundException;
import br.odravison.sogo.minicoursesdomain.presentation.minicourse.CreateMiniCourseRequest;
import br.odravison.sogo.minicoursesdomain.presentation.minicourse.ReadMiniCourseResponse;
import br.odravison.sogo.minicoursesdomain.presentation.minicourse.ReadMiniCoursesResponse;

import java.util.Collections;
import java.util.Optional;

public class MiniCourseAPI {

    private MiniCourseRepository miniCourseRepository;
    private StudentRepository studentRepository;

    public MiniCourseAPI(MiniCourseRepository miniCourseRepository,
                         StudentRepository studentRepository) {
        this.miniCourseRepository = miniCourseRepository;
        this.studentRepository = studentRepository;
    }

    public ReadMiniCoursesResponse readMiniCourses() {
        return new ReadMiniCoursesResponse(this.miniCourseRepository.readMinniCourses());
    }

    public ReadMiniCourseResponse createMiniCourse(CreateMiniCourseRequest createMiniCourseRequest) {
        return this.miniCourseRepository.create(createMiniCourseRequest);
    }

    public ReadMiniCourseResponse findById(Long id){

        ReadMiniCourseResponse response = null;
        Optional<MiniCourse> miniCourseOptional = this.miniCourseRepository.findById(id);
        if (miniCourseOptional.isPresent()) {
            MiniCourse miniCourse = miniCourseOptional.get();
            response = new ReadMiniCourseResponse(
                    miniCourse.getId(), miniCourse.getName(), miniCourse.getStartDate(),
                    miniCourse.getDuration(), miniCourse.getVacanciesNumber(),
                    miniCourse.getProfessorOwner().getId()
            );
        }

        return response;
    }

    public void subscribeStudentInMiniCourse(Long miniCourseId, Long studentId)
            throws MiniCourseNotFoundException, MiniCourseDomainException, StudentNotFoundException {
        if (!this.miniCourseRepository.existsById(miniCourseId)) {
            throw new MiniCourseNotFoundException("Mini Course not found");
        }

        MiniCourse miniCourse = this.miniCourseRepository.findById(miniCourseId).get();
        Optional<Student> studentOptional = this.studentRepository.findById(studentId);
        if (!studentOptional.isPresent()){
            throw new StudentNotFoundException("Student not found");
        }

        miniCourse.addStudentToMiniCourse(studentOptional.get());
        this.miniCourseRepository.save(miniCourse);

    }
}
