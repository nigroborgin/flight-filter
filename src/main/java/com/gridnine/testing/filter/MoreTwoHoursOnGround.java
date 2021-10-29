package com.gridnine.testing.filter;

import com.gridnine.testing.flights.Flight;
import com.gridnine.testing.flights.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MoreTwoHoursOnGround implements FlightFilter {

    @Override
    public boolean check(Flight flight) {
        if (flight == null) {
            throw new NullPointerException("This flight is null");
        }

        List<Segment> segments = flight.getSegments();

        if (segments.size() == 0) {
            throw new IllegalArgumentException("This flight does not contain segments");

        } else if (segments.size() > 1) {
            int totalTimeOnGroundInMinutes = 0;
            for (int i = 0; i < segments.size() - 1; i++) {
                LocalDateTime arrival = segments.get(i).getArrivalDate();
                LocalDateTime departureNextSegment = segments.get(i + 1).getDepartureDate();
                totalTimeOnGroundInMinutes += arrival.until(departureNextSegment, ChronoUnit.MINUTES);
            }
            return totalTimeOnGroundInMinutes > 120;
        }
        return false;
    }
}