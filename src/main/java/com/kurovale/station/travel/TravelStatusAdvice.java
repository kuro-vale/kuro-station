package com.kurovale.station.travel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TravelStatusAdvice
{
    @ResponseBody
    @ExceptionHandler(TravelStatusException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String travelStatusHandler(TravelStatusException exception)
    {
        return exception.getMessage();
    }
}
