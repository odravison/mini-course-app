package br.odravison.sogo.minicoursesdomain.infrasctructure;

import br.odravison.sogo.minicoursesdomain.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    List<Student> readStudents();

    Long create(Student student);

    Optional<Student> read(Long id);

    void update(Student student);

    void delete(Long id);

}
