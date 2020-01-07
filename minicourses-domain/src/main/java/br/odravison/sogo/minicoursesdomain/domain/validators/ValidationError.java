package br.odravison.sogo.minicoursesdomain.domain.validators;

public enum ValidationError {

    EMPTY_FIELD("EMPTY_FIELD", "Field cannot be empty"),
    INVALID_EMAIL("INVALID_EMAIL", "Invalid email format");

    public final String code;
    public final String message;

    ValidationError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
