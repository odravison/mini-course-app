package br.odravison.sogo.minicoursesdomain.presentation.student;

import lombok.Getter;

import java.util.List;

@Getter
public class ReadStudentsResponse {

    private List<ReadStudentResponse> students;

    public ReadStudentsResponse(List<ReadStudentResponse> students) {
        this.students = students;
    }

}
