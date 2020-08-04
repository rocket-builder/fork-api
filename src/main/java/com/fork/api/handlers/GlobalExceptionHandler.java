package com.fork.api.handlers;

import com.fork.api.exceptions.*;
import com.fork.api.models.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.text.ParseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> noHandlerFoundException(NoHandlerFoundException ex) {

        return new ResponseEntity<>(new ApiError(
                404, "No handler found for your request."
        ), HttpStatus.OK);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> methodNotAllowedException(HttpRequestMethodNotSupportedException ex) {

        return new ResponseEntity<>(new ApiError(
                405, "Method not allowed."
        ), HttpStatus.OK);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> missingServletRequestParameterException(MissingServletRequestParameterException ex) {

        return new ResponseEntity<>(new ApiError(
                400, ex.getMessage()
        ), HttpStatus.OK);
    }

    @ExceptionHandler(LoginTakenException.class)
    public ResponseEntity<ApiError> loginTakenException(LoginTakenException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "User login already taken :("
        ), HttpStatus.OK);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> userNotFoundException(UserNotFoundException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "User not found :("
        ), HttpStatus.OK);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ApiError> parseException(ParseException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "Incorrect request data :("
        ), HttpStatus.OK);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ApiError> incorrectPasswordException(IncorrectPasswordException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "Incorrect password :("
        ), HttpStatus.OK);
    }

    @ExceptionHandler(BookmakerNotFoundException.class)
    public ResponseEntity<ApiError> bookmakerNotFoundException(BookmakerNotFoundException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "Bookmaker not founded :("
        ), HttpStatus.OK);
    }

    @ExceptionHandler(BookmakerAlreadyExistsException.class)
    public ResponseEntity<ApiError> bookmakerAlreadyExistsException(BookmakerAlreadyExistsException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "Such bookmaker already exists :("
        ), HttpStatus.OK);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> accessDeniedException(AccessDeniedException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "Access denied for this action :("
        ), HttpStatus.OK);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiError> invalidTokenException(InvalidTokenException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "Your token is invalid :("
        ), HttpStatus.OK);
    }

    @ExceptionHandler(BkAccountNotFoundException.class)
    public ResponseEntity<ApiError> bkAccountNotFoundException(BkAccountNotFoundException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "Bk Account not founded :("
        ), HttpStatus.OK);
    }

    @ExceptionHandler(BkAccountAlreadyExistsException.class)
    public ResponseEntity<ApiError> bkAccountAlreadyExistsException(BkAccountAlreadyExistsException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "Such Bk Account with this user and this bk already exists :("
        ), HttpStatus.OK);
    }

    @ExceptionHandler(NegativeBalanceException.class)
    public ResponseEntity<ApiError> negativeBalanceException(NegativeBalanceException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "Bk Account balance cannot be less than zero :("
        ), HttpStatus.OK);
    }
    // More exception handlers here ...
}
