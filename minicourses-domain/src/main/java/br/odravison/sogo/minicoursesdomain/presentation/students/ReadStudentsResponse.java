package br.odravison.sogo.minicoursesdomain.presentation.students;

import br.odravison.sogo.minicoursesdomain.domain.Professor;
import br.odravison.sogo.minicoursesdomain.domain.Student;

import java.util.List;

public class ReadStudentsResponse {

    private List<Student> students;

    public ReadStudentsResponse(List<Student> students) {
        this.students = students;
    }

}
