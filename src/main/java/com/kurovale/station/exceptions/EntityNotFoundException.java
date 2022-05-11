package com.kurovale.station.exceptions;

public class EntityNotFoundException extends RuntimeException
{
    public EntityNotFoundException(Long id, Class<?> entity)
    {
        super("Could not find " + entity.getSimpleName() + " with id: " + id);
    }
}
