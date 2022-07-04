package com.leonardolelli.LibraryGateway.controller;

import java.net.ConnectException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.leonardolelli.LibraryGateway.exception.InvalidOperationException;

@ControllerAdvice
public class GatewayControllerAdvice {

    @ExceptionHandler(ConnectException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    void connectHandle(ConnectException exc) {
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    void notFoundHandle(HttpClientErrorException.NotFound exc) {
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    void badRequestHandle(HttpClientErrorException.BadRequest exc) {
    }

    @ExceptionHandler(HttpServerErrorException.ServiceUnavailable.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    void serviceUnavaliableHandle(HttpServerErrorException.ServiceUnavailable exc) {
    }

    @ExceptionHandler(HttpClientErrorException.Conflict.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    void conflictHandle(HttpClientErrorException.Conflict exc) {
    }

    @ExceptionHandler(InvalidOperationException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    void invalidOperationHandle(InvalidOperationException exc) {
    }

    @ExceptionHandler(ResourceAccessException.class)
    @ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
    void timeoutHandle(ResourceAccessException exc) {
    }

}
