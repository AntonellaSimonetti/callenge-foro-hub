package ar.antonella.forohub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ar.antonella.forohub.models.dto.ErrorResponse;
import ar.antonella.forohub.utils.exceptions.TopicoNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleValidFiel(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return new ErrorResponse(
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage(),
                LocalDateTime.now(),
                List.of("El recurso solicitado no existe")
        );
    }

    @ExceptionHandler(TopicoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(TopicoNotFoundException ex) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage(),
                LocalDateTime.now(),
                List.of("El recurso solicitado no existe")
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalError(Exception ex) {
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                "Error interno del servidor",
                LocalDateTime.now(),
                List.of(ex.getClass().getSimpleName())
        );
    }

}
