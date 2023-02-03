package com.jwtproject.exception;

import com.jwtproject.exception.dto.ErrorResponseDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestController
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LocalB2CExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequest400Exception.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponseDto exceptionBadRequest (BadRequest400Exception ex) {
        return new ErrorResponseDto(ex);
    }

    @ExceptionHandler(NotFound404Exception.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponseDto exceptionNotFound (NotFound404Exception ex) {
        return new ErrorResponseDto(ex);
    }

    @ExceptionHandler(Unauthorized401Exception.class)
    @ResponseStatus(UNAUTHORIZED)
    public ErrorResponseDto exceptionUnauthorized (Unauthorized401Exception ex) {
        return new ErrorResponseDto(ex);
    }

    @ExceptionHandler(Forbidden403Exception.class)
    @ResponseStatus(FORBIDDEN)
    public ErrorResponseDto exceptionForbidden (Forbidden403Exception ex) {
        return new ErrorResponseDto(ex);
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ErrorResponseDto handleAuthenticationException ( ) {
        return new ErrorResponseDto(UNAUTHORIZED, "login.incorrect_credentials");
    }

    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(FORBIDDEN)
    public ErrorResponseDto exceptionBlocked (DisabledException ex) {
        return new ErrorResponseDto(FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(Conflict409Exception.class)
    @ResponseStatus(CONFLICT)
    public ErrorResponseDto exceptionConflict (Conflict409Exception ex) {
        return new ErrorResponseDto(ex);
    }


}
