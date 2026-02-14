package com.carrental.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> validation(MethodArgumentNotValidException ex) {
    Map<String, Object> body = new HashMap<>();
    body.put("error", "VALIDATION_ERROR");
    body.put("message", ex.getBindingResult().getFieldErrors().stream()
        .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
        .toList());
    return ResponseEntity.badRequest().body(body);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<?> badRequest(BadRequestException ex) {
    return ResponseEntity.badRequest().body(Map.of("error", "BAD_REQUEST", "message", ex.getMessage()));
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> notFound(NotFoundException ex) {
    return ResponseEntity.status(404).body(Map.of("error", "NOT_FOUND", "message", ex.getMessage()));
  }
}
