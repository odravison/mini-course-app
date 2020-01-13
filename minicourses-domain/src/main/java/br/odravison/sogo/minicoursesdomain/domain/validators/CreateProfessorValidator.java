package br.odravison.sogo.minicoursesdomain.domain.validators;

import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseError;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateProfessorValidator {

    public static List<MiniCourseError> validate(String name, String email, String password, String registration,
                                                 List<String> phones){

        List<MiniCourseError> errors = new ArrayList<>();
        if (StringUtils.isBlank(registration)) {

            errors.add(new MiniCourseError(
                    ValidationError.EMPTY_FIELD.code,
                    "registration",
                    ValidationError.EMPTY_FIELD.message));

        }

        if (StringUtils.isBlank(email)) {

            errors.add(new MiniCourseError(
                    ValidationError.EMPTY_FIELD.code,
                    "email",
                    ValidationError.EMPTY_FIELD.message));

        }

        if (StringUtils.isBlank(password)) {

            errors.add(new MiniCourseError(
                    ValidationError.EMPTY_FIELD.code,
                    "password",
                    ValidationError.EMPTY_FIELD.message));

        }

        if (StringUtils.isBlank(name)) {

            errors.add(new MiniCourseError(
                    ValidationError.EMPTY_FIELD.code,
                    "name",
                    ValidationError.EMPTY_FIELD.message));

        }

        return errors;
    }
}
