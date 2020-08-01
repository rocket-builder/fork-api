package com.fork.api.handlers;

import com.fork.api.exceptions.IncorrectPasswordException;
import com.fork.api.exceptions.LoginTakenException;
import com.fork.api.exceptions.UserNotFoundException;
import com.fork.api.models.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> methodNotAllowedException(HttpRequestMethodNotSupportedException ex) {

        return new ResponseEntity<>(new ApiError(
                405, "Method not allowed."
        ), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(LoginTakenException.class)
    public ResponseEntity<ApiError> loginTakenException(LoginTakenException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "User login already taken."
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> userNotFoundException(UserNotFoundException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "User not found."
        ), HttpStatus.OK);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ApiError> parseException(ParseException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "Incorrect request data."
        ), HttpStatus.OK);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ApiError> incorrectPasswordException(IncorrectPasswordException ex) {

        return new ResponseEntity<>(new ApiError(
                400, "Incorrect password."
        ), HttpStatus.OK);
    }
    // More exception handlers here ...
}
