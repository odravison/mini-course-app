package br.odravison.sogo.minicoursesdomain.domain;


import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseDomainException;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MiniCourse {

    private Long id;
    private String name;
    private Date startDate;
    private Duration duration;
    private Integer vacanciesNumber;
    private List<Student> participants = new ArrayList<>();
    protected Boolean deleted;

    private Professor professorOwner;

    public MiniCourse(Long id, String name, Date startDate, Duration duration, Integer vacanciesNumber, Professor professorOwner) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.duration = duration;
        this.vacanciesNumber = vacanciesNumber;
        this.professorOwner = professorOwner;
    }

    public MiniCourse(Long id, String name, Date startDate, Duration duration, Integer vacanciesNumber) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.duration = duration;
        this.vacanciesNumber = vacanciesNumber;
    }

    public void addStudentToMiniCourse(Student student) throws MiniCourseDomainException {
        if (this.participants.size() >= this.vacanciesNumber) {
            throw new MiniCourseDomainException(
                    Collections.singletonList(
                            new MiniCourseError(
                                    "Mini Course with no vacancies",
                                    null,
                                    "There is no vacancies in this Mini Course"
                            )
                    )
            );
        }

        this.participants.add(student);

    }

}
