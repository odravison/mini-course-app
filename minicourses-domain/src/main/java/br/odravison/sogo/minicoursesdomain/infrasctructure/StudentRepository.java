package br.odravison.sogo.minicoursesdomain.infrasctructure;

import br.odravison.sogo.minicoursesdomain.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    List<Student> readStudents();

    Student create(Student student);

    Optional<Student> read(Long id);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    Optional<Student> findById(Long studentId);
}
