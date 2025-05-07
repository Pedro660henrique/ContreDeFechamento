package com.aro.FechamentoAro.Security;

import com.aro.FechamentoAro.Security.Config.ErrorResponse;
import com.aro.FechamentoAro.Security.Config.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class ControleSeguranca extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
            "USUARIO_NAO_ENCONTRADO",
            ex.getMessage(),
            new Date()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
            "CREDENCIAIS_INVALIDAS",
            "Email ou senha incorretos",
            new Date()
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({DisabledException.class, LockedException.class})
    public ResponseEntity<ErrorResponse> handleDisabledOrLockedException(Exception ex, WebRequest request) {
        String message = ex instanceof DisabledException ? 
            "Conta desativada" : "Conta bloqueada devido a múltiplas tentativas falhas";
        
        ErrorResponse error = new ErrorResponse(
            "CONTA_INATIVA",
            message,
            new Date()
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({JwtException.class, AutenticacaoException.class})
    public ResponseEntity<ErrorResponse> handleJwtAndAuthException(RuntimeException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
            "ERRO_AUTENTICACAO",
            ex.getMessage(),
            new Date()
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
            "ERRO_INTERNO",
            "Ocorreu um erro interno no servidor",
            new Date()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
