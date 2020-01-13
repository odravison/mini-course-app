package br.odravison.sogo.minicoursesapplication.ui.rest.minicourse.responses;

import br.odravison.sogo.minicoursesdomain.presentation.minicourse.ReadMiniCourseResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import java.time.Duration;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MiniCourseJSONResponse {

    private Long id;
    private String name;
    private Date startDate;
    private Duration duration;
    private Integer vacanciesNumber;
    private Long professorOwnerId;

    public static MiniCourseJSONResponse createMiniCourseJSONResponse(ReadMiniCourseResponse miniCourseResponse) {
        MiniCourseJSONResponse response = new MiniCourseJSONResponse();

        response.id = miniCourseResponse.getId();
        response.name = miniCourseResponse.getName();
        response.startDate = miniCourseResponse.getStartDate();
        response.duration = miniCourseResponse.getDuration();
        response.vacanciesNumber = miniCourseResponse.getVacanciesNumber();
        response.professorOwnerId = miniCourseResponse.getProfessorOwnerId();

        return response;
    }
}
