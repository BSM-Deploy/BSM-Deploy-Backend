package bssm.deploy.global.error;

import bssm.deploy.global.error.exceptions.BadRequestException;
import bssm.deploy.global.error.exceptions.InternalServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<GeneralErrorResponse> handleException(GeneralException e) {
        GeneralErrorResponse response = new GeneralErrorResponse(e);
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getStatusCode()));
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<GeneralErrorResponse> handleException(InternalServerException e) {
        e.printStackTrace();
        GeneralErrorResponse response = new GeneralErrorResponse(e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ValidationErrorResponse> bindException(BadRequestException e) {
        ValidationErrorResponse response = new ValidationErrorResponse(e.getFields());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> bindException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError error : e.getFieldErrors()) {
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        ValidationErrorResponse response = new ValidationErrorResponse(errorMap);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ValidationErrorResponse> handleConstraintViolation(ConstraintViolationException e) {
        Map<String, String> errorMap = new HashMap<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            errorMap.put(
                    propertyPath.substring(propertyPath.lastIndexOf(".") + 1),
                    violation.getMessage());
        }
        ValidationErrorResponse response = new ValidationErrorResponse(errorMap);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralErrorResponse> handleException(Exception e) {
        e.printStackTrace();
        GeneralErrorResponse response = new GeneralErrorResponse(new GeneralException());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}