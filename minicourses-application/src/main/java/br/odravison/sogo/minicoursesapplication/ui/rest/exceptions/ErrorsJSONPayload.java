package br.odravison.sogo.minicoursesapplication.ui.rest.exceptions;

import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseError;

import java.util.ArrayList;
import java.util.List;

public class ErrorsJSONPayload {
    public final List<ErrorJSON> errors;

    public ErrorsJSONPayload(List<MiniCourseError> applicationErrors) {
        this.errors = new ArrayList<>();
        applicationErrors.forEach(applicationError -> errors.add(fromApplicationError(applicationError)));
    }

    private ErrorJSON fromApplicationError(MiniCourseError applicationError) {
        return new ErrorJSON(applicationError.code, applicationError.field, applicationError.description);
    }
}
