package br.odravison.sogo.minicoursesdomain.presentation.minicourse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReadMiniCoursesResponse {

    private List<ReadMiniCourseResponse> miniCourses;

    public ReadMiniCoursesResponse(List<ReadMiniCourseResponse> miniCourses) {
        this.miniCourses = miniCourses;
    }
}
