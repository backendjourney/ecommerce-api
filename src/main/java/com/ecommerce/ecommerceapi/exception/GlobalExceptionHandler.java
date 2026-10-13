package com.ecommerce.ecommerceapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationError(MethodArgumentNotValidException e) {
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach((error) -> response.put(error.getField(), error.getDefaultMessage()));
        ProblemDetail problemDetail = buildProblem(HttpStatus.BAD_REQUEST, "Validation failed");
        problemDetail.setProperty("errors", response);
        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException e) {
        return buildProblem(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(APIException.class)
    public ProblemDetail handleApiException(APIException e) {
        return buildProblem(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    private ProblemDetail buildProblem(HttpStatus status, String detail) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
}
