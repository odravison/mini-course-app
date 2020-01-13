package br.odravison.sogo.minicoursesdomain.presentation.minicourse;

import br.odravison.sogo.minicoursesdomain.domain.MiniCourse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReadMiniCourseResponse {

    private Long id;
    private String name;
    private Date startDate;
    private Duration duration;
    private Integer vacanciesNumber;
    private Long professorOwnerId;

    public ReadMiniCourseResponse(Long id, String name, Date startDate, Duration duration, Integer vacanciesNumber, Long professorOwnerId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.duration = duration;
        this.vacanciesNumber = vacanciesNumber;
        this.professorOwnerId = professorOwnerId;
    }

    public ReadMiniCourseResponse(MiniCourse miniCourse) {
        this.id = miniCourse.getId();
        this.name = miniCourse.getName();
        this.startDate = miniCourse.getStartDate();
        this.duration = miniCourse.getDuration();
        this.vacanciesNumber = miniCourse.getVacanciesNumber();
        this.professorOwnerId = miniCourse.getProfessorOwner().getId();
    }
}
