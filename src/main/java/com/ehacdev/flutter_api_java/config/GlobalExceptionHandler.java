package com.ehacdev.flutter_api_java.config;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ehacdev.flutter_api_java.exceptions.EntityNotFoundException;
import com.ehacdev.flutter_api_java.exceptions.ModelNotFoundException;
import com.ehacdev.flutter_api_java.exceptions.ServiceException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleModelNotFoundException(ModelNotFoundException ex) {
        System.out.println("Erreur: " + ex.getMessage());
        return buildErrorResponse("Ressource non trouvée", HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String, Object>> handleServiceException(ServiceException ex) {
        System.out.println("Erreur: " + ex.getMessage());
        return buildErrorResponse("Erreur dans le service", HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> response = new HashMap<>();
        response.put("status", "ECHEC");
        response.put("message", "Validation échouée");
        response.put("data", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        System.out.println("Erreur: " + ex.getMessage());
        return buildErrorResponse("Erreur interne du serveur", HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(EntityNotFoundException ex) {
        System.out.println("Erreur: " + ex.getMessage());
        return buildErrorResponse("Entité non trouvée", HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status, String error) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ECHEC");
        response.put("message", message);
        response.put("data", error);
        return ResponseEntity.status(status).body(response);
    }

}