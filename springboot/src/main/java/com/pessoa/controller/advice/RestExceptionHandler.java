package com.pessoa.controller.advice;

import com.pessoa.controller.dto.response.ErrorResponse;
import com.pessoa.exception.BadRequestException;
import com.pessoa.exception.NotFoundException;
import com.pessoa.exception.InternalServerException;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({InternalServerException.class, Exception.class})
    public ErrorResponse handleInternalServerError(
            Exception exception,
            HttpServletRequest request) {

        LOG.error("Erro interno não mapeado: {}", exception.getMessage(), exception);

        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse handleNotFoundError(
            NotFoundException exception,
            HttpServletRequest request) {

        LOG.warn("Recurso não encontrado: {}", exception.getMessage());

        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                NOT_FOUND.value(),
                NOT_FOUND.getReasonPhrase(),
                exception.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse handleBadRequest(
            BadRequestException exception,
            HttpServletRequest request) {

        LOG.warn("Bad Request: {}", exception.getMessage());

        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                exception.getMessage()
        );
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors()
                .forEach(v -> errors.add(v.getDefaultMessage()));

        LOG.warn("Erro de validação: {}", errors);

        return new ErrorResponse(
                LocalDateTime.now(),
                request.getServletPath(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                errors.toString()
        );
    }
}
