package br.odravison.sogo.minicoursesapplication.ui.rest.exceptions;

import br.odravison.sogo.minicoursesdomain.presentation.exceptions.MiniCourseDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MiniCourseDomainException.class)
    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<ErrorsJSONPayload> handleMiniCourseDomainException(MiniCourseDomainException e) {
        return new ResponseEntity<>(new ErrorsJSONPayload(e.getErrors()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>("{\"errors\":[{\"code\":\"NOT_FOUND\"}]}", HttpStatus.NOT_FOUND);

    }

}
