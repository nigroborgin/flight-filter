package com.gridnine.testing.filter;

import com.gridnine.testing.flights.Flight;
import com.gridnine.testing.flights.Segment;

import java.time.LocalDateTime;
import java.util.List;

public class ArrivalBeforeDeparture implements FlightFilter {

    @Override
    public boolean check(Flight flight) {
        List<Segment> segments = flight.getSegments();

        if (segments.size() == 1) {

            LocalDateTime departure = segments.get(0).getDepartureDate();
            LocalDateTime arrival = segments.get(0).getArrivalDate();
            return arrival.isBefore(departure);

        } else {
            for (int i = 0; i < segments.size() - 1; i++) {

                LocalDateTime departure = segments.get(i).getDepartureDate();
                LocalDateTime arrival = segments.get(i).getArrivalDate();
                LocalDateTime departureNextSegment = segments.get(i+1).getDepartureDate();

                if (arrival.isBefore(departure) || departureNextSegment.isBefore(arrival)) {
                    return true;
                }
            }
        }
        return false;
    }
}
