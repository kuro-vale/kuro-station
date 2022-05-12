package com.kurovale.station.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EntityStatusAdvice
{
    @ResponseBody
    @ExceptionHandler(EntityStatusException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String entityStatusHandler(EntityStatusException exception)
    {
        return exception.getMessage();
    }
}
