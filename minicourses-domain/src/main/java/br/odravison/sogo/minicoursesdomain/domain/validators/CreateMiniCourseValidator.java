package br.odravison.sogo.minicoursesdomain.domain.validators;

import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseError;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateMiniCourseValidator {

    public static List<MiniCourseError> validate(String name, Date startDate, Duration duration,
                                                 Integer vacanciesNumber){

        List<MiniCourseError> errors = new ArrayList<>();
        if (!StringUtils.isBlank(name)) {

            errors.add(new MiniCourseError(
                    ValidationError.EMPTY_FIELD.code,
                    "name",
                    ValidationError.EMPTY_FIELD.message));

        }

        if (startDate == null) {

            errors.add(new MiniCourseError(
                    ValidationError.EMPTY_FIELD.code,
                    "name",
                    ValidationError.EMPTY_FIELD.message));

        }

        if (duration == null) {

            errors.add(new MiniCourseError(
                    ValidationError.EMPTY_FIELD.code,
                    "name",
                    ValidationError.EMPTY_FIELD.message));

        }

        if (vacanciesNumber == null) {

            errors.add(new MiniCourseError(
                    ValidationError.EMPTY_FIELD.code,
                    "name",
                    ValidationError.EMPTY_FIELD.message));

        } else if (vacanciesNumber < 1){

            errors.add(new MiniCourseError(
                    ValidationError.EMPTY_FIELD.code,
                    "name",
                    ValidationError.EMPTY_FIELD.message));

        }

        return errors;
    }
}
