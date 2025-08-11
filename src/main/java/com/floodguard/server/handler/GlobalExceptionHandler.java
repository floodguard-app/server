package com.floodguard.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.floodguard.server.exception.EmailAlreadyExistsException;

import jakarta.validation.ConstraintViolationException;

// A anotação @ControllerAdvice faz com que o Spring detecte esta classe
// como um "ouvinte global" para exceções lançadas em todos os @Controllers.
@ControllerAdvice
public class GlobalExceptionHandler {
    // Uso de um logger para registrar erros de forma padronizada.
    // Isso é uma prática melhor do que usar System.out.println.
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Este método trata exceções de validação de DTOs, que ocorrem quando
    // as anotações @Valid em um controller falham.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
            .map(error -> {
                String defaultMessage = error.getDefaultMessage();
                if (error instanceof org.springframework.validation.FieldError) {
                    String fieldName = ((org.springframework.validation.FieldError) error).getField();
                    return String.format("%s: %s", fieldName, defaultMessage);
                }
                return defaultMessage;
            })
            .findFirst()
            .orElse("Erro de validação não especificado.");

        return ResponseEntity.badRequest().body(errorMessage);
    }
    
    // Trata uma exceção de regra de negócio personalizada, lançada pelo service.
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // Trata exceções de violação de integridade de dados do banco de dados (ex: chave duplicada).
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String message = "Erro ao processar a requisição";
        if (e.getCause() instanceof ConstraintViolationException) {
            message = "Violação de constraint: " + e.getCause().getMessage();
        }
        return ResponseEntity.badRequest().body(message);
    }

    // Trata exceções de falha na autenticação (senha ou usuário incorretos).
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    // Este é o método "catch-all", que trata qualquer exceção não tratada pelos
    // métodos acima. Ele atua como uma rede de segurança.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        logger.error("Ocorreu um erro interno no servidor:", e);
        return ResponseEntity.internalServerError().body("Ocorreu um erro interno. Por favor, tente novamente mais tarde.");
    }
}