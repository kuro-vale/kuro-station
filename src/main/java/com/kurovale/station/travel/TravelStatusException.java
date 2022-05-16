package com.kurovale.station.travel;

import java.util.Collection;

public class TravelStatusException extends RuntimeException
{
    TravelStatusException(Collection<TravelStatus> statuses)
    {
        super("Cannot do that to a travel that is in these statuses: " + statuses);
    }
}
