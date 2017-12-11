package ua.epam.spring.hometask.advisor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerAdvisor {

    @ExceptionHandler({ConstraintViolationException.class})
    public HttpEntity<Map<String, Map<String, String>>> constraintViolation(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        Map<String, Map<String, String>> response = new HashMap<>();

        e.getConstraintViolations()
                .stream()
                .filter(violation -> violation.getPropertyPath() != null)
                .forEach(violation -> putError(violation, errors));

        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private void putError(ConstraintViolation<?> violation, Map<String, String> errors) {
        errors.put(violation.getPropertyPath().toString(), violation.getMessage());
    }
}