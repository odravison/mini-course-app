package br.odravison.sogo.minicoursesdomain.domain.validators;

public enum ValidationError {

    EMPTY_FIELD("EMPTY_FIELD", "Field cannot be empty"),
    INVALID_EMAIL("INVALID_EMAIL", "Invalid email format"),
    INVALID_BIRTHDAY("INVALID_BIRTHDAY", "Invalid birthday date, cannot be after today"),
    EMAIL_ALREADY_IN_USE("EMAIL_ALREADY_IN_USE", "This email is already in use. Please choose another"),
    REGISTRATION_ALREADY_IN_USE("REGISTRATION_ALREADY_IN_USE", "This registration is already in use"),
    CPF_ALREADY_IN_USE("CPF_ALREADY_IN_USE", "This CPF is already in use. Please, verify your document"),
    IS_NOT_STUDENT("IS_NOT_STUDENT", "You must be a student to create a Mini Course"),
    IS_NOT_PROFESSOR("IS_NOT_PROFESSOR", "You must be a professor to create a Mini Course");

    public final String code;
    public final String message;

    ValidationError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
