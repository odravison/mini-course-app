package br.odravison.sogo.minicoursesapplication.ui.rest.minicourse.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MiniCourseJSONCreationRequest {

    private String name;
    private Date startDate;
    private Duration duration;
    private Integer vacanciesNumber;

}
