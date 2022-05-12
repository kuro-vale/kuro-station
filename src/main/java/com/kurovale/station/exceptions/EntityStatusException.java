package com.kurovale.station.exceptions;

public class EntityStatusException extends RuntimeException
{
    public EntityStatusException(EntityStatus status, Class<?> entity)
    {
        super("This " + entity.getSimpleName() + " is already " + status);
    }
}
