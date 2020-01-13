package br.odravison.sogo.minicoursesdomain.presentation.minicourse;

import br.odravison.sogo.minicoursesdomain.domain.validators.CreateMiniCourseValidator;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseDomainException;
import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseError;
import lombok.Getter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class CreateMiniCourseRequest {

    private String name;
    private Date startDate;
    private Duration duration;
    private Integer vacanciesNumber;

    private Long professorOwnerId;

    private CreateMiniCourseRequest(String name,
                                    Date startDate,
                                    Duration duration,
                                    Integer vacanciesNumber,
                                    Long professorOwnerId) {
        this.name = name;
        this.startDate = startDate;
        this.duration = duration;
        this.vacanciesNumber = vacanciesNumber;
        this.professorOwnerId = professorOwnerId;
    }

    public static CreateMiniCourseRequest createMiniCourseRequest(
            String name, Date startDate, Duration duration,
            Integer vacanciesNumber, Long professorOwnerId) throws MiniCourseDomainException {
        validate(name, startDate, duration, vacanciesNumber);
        return new CreateMiniCourseRequest(name, startDate, duration, vacanciesNumber, professorOwnerId);
    }

    public static void validate(String name, Date startDate, Duration duration,
                                Integer vacanciesNumber) throws MiniCourseDomainException {

        List<MiniCourseError> errors = new ArrayList<>();

        errors.addAll(CreateMiniCourseValidator.validate(name, startDate, duration, vacanciesNumber));

        if (errors.isEmpty()) {
            throw new MiniCourseDomainException(errors);
        }

    }
}
