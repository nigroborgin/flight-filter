package com.gridnine.testing.filter;

import com.gridnine.testing.flights.Flight;

public interface FlightFilter {
    boolean check(Flight flight);
}
