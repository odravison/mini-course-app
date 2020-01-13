package br.odravison.sogo.minicoursesdomain.presentation.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MiniCourseError {

    public final String code;
    public final String field;
    public final String description;

    public MiniCourseError(String code, String field, String description) {
        this.code = code;
        this.field = field;
        this.description = description;
    }
}
