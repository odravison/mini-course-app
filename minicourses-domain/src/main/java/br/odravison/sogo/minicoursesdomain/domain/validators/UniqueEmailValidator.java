package br.odravison.sogo.minicoursesdomain.domain.validators;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class UniqueEmailValidator {

    private static final String EMAIL_REGEX_VALIDATOR = "^(.+)@(.+)$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX_VALIDATOR);

    public static List<ValidationError> valid(String email){
        if (StringUtils.isBlank(email)) {
            return Collections.singletonList(ValidationError.EMPTY_FIELD);
        } else if (!pattern.matcher(email).matches()) {
            return Collections.singletonList(ValidationError.INVALID_EMAIL);
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
