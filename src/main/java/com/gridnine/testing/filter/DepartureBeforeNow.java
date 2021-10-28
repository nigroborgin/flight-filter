package com.gridnine.testing.filter;

import com.gridnine.testing.flights.Flight;
import com.gridnine.testing.flights.Segment;

import java.time.LocalDateTime;

public class DepartureBeforeNow implements FlightFilter {

    @Override
    public boolean check(Flight flight) {
        for (Segment segment : flight.getSegments()) {

            LocalDateTime departure = segment.getDepartureDate();
            LocalDateTime now = LocalDateTime.now();

            if (departure.isBefore(now)) {
                return true;
            }
        }
        return false;
    }
}
