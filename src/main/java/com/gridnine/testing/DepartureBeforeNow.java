package com.gridnine.testing;

import java.time.LocalDateTime;

public class DepartureBeforeNow implements FlightFilter {

    @Override
    public boolean check(Flight flight) {
        for (Segment segment : flight.getSegments()) {
            if (segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }
}
