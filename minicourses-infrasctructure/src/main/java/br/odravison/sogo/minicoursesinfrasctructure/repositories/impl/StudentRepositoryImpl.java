package br.odravison.sogo.minicoursesinfrasctructure.repositories.impl;

import br.odravison.sogo.minicoursesdomain.domain.Student;
import br.odravison.sogo.minicoursesdomain.infrasctructure.StudentRepository;
import br.odravison.sogo.minicoursesinfrasctructure.entities.StudentMapped;
import br.odravison.sogo.minicoursesinfrasctructure.mapper.StudentMapper;
import br.odravison.sogo.minicoursesinfrasctructure.repositories.hibernate.StudentMappedRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {

    private StudentMappedRepository repository;

    public StudentRepositoryImpl(StudentMappedRepository studentMappedRepository) {
        this.repository = studentMappedRepository;
    }


    @Override
    public List<Student> readStudents() {
        List<Student> response = new ArrayList<>();

        this.repository.findAll().forEach(studentMapped -> {
            response.add(StudentMapper.fromStudentMapped(studentMapped));
        });

        return response;
    }

    @Override
    public Student create(Student student) {
        StudentMapped studentMapped = StudentMapper.toStudentMapped(student);
        studentMapped = this.repository.save(studentMapped);
        return StudentMapper.fromStudentMapped(studentMapped);
    }

    @Override
    public Optional<Student> read(Long id) {
        Optional<StudentMapped> studentFound = this.repository.findById(id);
        return studentFound.map(StudentMapper::fromStudentMapped);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.repository.existsByEmailIgnoreCaseAndDeletedFalse(email);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return this.repository.existsByCpfAndDeletedFalse(cpf);
    }

    @Override
    public Optional<Student> findById(Long studentId) {
        return Optional.of(
                this.repository.findById(studentId).map(StudentMapper::fromStudentMapped)
        ).orElse(Optional.empty());
    }


}
