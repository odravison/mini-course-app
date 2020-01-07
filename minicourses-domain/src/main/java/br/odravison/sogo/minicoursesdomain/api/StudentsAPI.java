package br.odravison.sogo.minicoursesdomain.api;

import br.odravison.sogo.minicoursesdomain.infrasctructure.StudentRepository;
import br.odravison.sogo.minicoursesdomain.presentation.students.ReadStudentsResponse;

import javax.transaction.Transactional;

public class StudentsAPI {

    private StudentRepository studentRepository;

    public StudentsAPI (StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Transactional
    public ReadStudentsResponse readStudents() {
        return new ReadStudentsResponse(this.studentRepository.readStudents());
    }
}
