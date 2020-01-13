package br.odravison.sogo.minicoursesdomain.presentation.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class MiniCourseDomainException extends Exception {

    private final List<MiniCourseError> errors;

    public MiniCourseDomainException(List<MiniCourseError> errors) {
        this.errors = errors;
    }
}
