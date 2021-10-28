package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MoreTwoHoursOnGround implements FlightFilter {

    @Override
    public boolean check(Flight flight) {
        List<Segment> segments = flight.getSegments();

        if (segments.size() > 1) {
            int totalTimeOnGroundInMinutes = 0;
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime arrival = segments.get(i).getArrivalDate();
                LocalDateTime departureNextSegment = segments.get(i + 1).getDepartureDate();
                totalTimeOnGroundInMinutes += arrival.until(departureNextSegment, ChronoUnit.MINUTES);
            }
            if (totalTimeOnGroundInMinutes > 120) {
                return true;
            }
        }
        return false;
    }
}
