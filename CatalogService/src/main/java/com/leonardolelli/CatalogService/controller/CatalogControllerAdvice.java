package com.leonardolelli.CatalogService.controller;

import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.leonardolelli.CatalogService.exception.GenreNotValidException;
import com.leonardolelli.CatalogService.exception.NoParametersException;

@RestControllerAdvice
public class CatalogControllerAdvice {

    @ExceptionHandler(SQLTransientConnectionException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "DB service not available")
    void SqlTransientHandle(SQLTransientConnectionException exc) {
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Element not present")
    void NoSuchElementHandle(NoSuchElementException exc) {
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Issues with your operation")
    void SQLHandle(SQLException exc) {
    }

    @ExceptionHandler(GenreNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The specified genre doesn't exist")
    void GenreNotValidHandle(GenreNotValidException exc) {
    }

    @ExceptionHandler(NoParametersException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No params specified")
    void NoParametersExceptionHandle(NoParametersException exc) {
    }

}
