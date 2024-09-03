package com.uzay.multiplerolebasedauthenticationapia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class GlobalHandlerException {
    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<String> handleUserNameNotFoundException(UserNameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Diğer istisnalar için handlerlar ekleyebilirsiniz
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("boş bırakılamaz");
    }
}

