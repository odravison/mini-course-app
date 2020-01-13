package br.odravison.sogo.minicoursesdomain.domain.validators;

import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseError;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateStudentValidator {

    public static List<MiniCourseError> validate(String name, String email, String password, String cpf, Date birthday){

        List<MiniCourseError> errors = new ArrayList<>();

        if (StringUtils.isBlank(cpf)) {

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

        if (birthday == null) {

            errors.add(new MiniCourseError(
                    ValidationError.EMPTY_FIELD.code,
                    "birthday",
                    ValidationError.EMPTY_FIELD.message));

        } else if (birthday.after(new Date())) {

            errors.add(new MiniCourseError(
                    ValidationError.INVALID_BIRTHDAY.code,
                    "birthday",
                    ValidationError.INVALID_BIRTHDAY.message));

        }

        return errors;
    }

}
