package com.ecole221.planification.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> entiteIntrouvable(EntityNotFoundException e) {
        return reponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> requeteInvalide(BadRequestException e) {
        return reponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, Object>> conflit(ConflictException e) {
        return reponse(HttpStatus.CONFLICT, e.getMessage());
    }

    /** Levee par Spring quand une annotation de validation d'un RequestDto n'est pas respectee. */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validation(MethodArgumentNotValidException e) {
        Map<String, String> champs = new HashMap<>();
        for (FieldError erreur : e.getBindingResult().getFieldErrors()) {
            champs.put(erreur.getField(), erreur.getDefaultMessage());
        }
        Map<String, Object> corps = corps(HttpStatus.BAD_REQUEST, "Requete invalide");
        corps.put("erreurs", champs);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corps);
    }

    private ResponseEntity<Map<String, Object>> reponse(HttpStatus statut, String message) {
        return ResponseEntity.status(statut).body(corps(statut, message));
    }

    private Map<String, Object> corps(HttpStatus statut, String message) {
        Map<String, Object> corps = new HashMap<>();
        corps.put("status", statut.value());
        corps.put("message", message);
        return corps;
    }
}