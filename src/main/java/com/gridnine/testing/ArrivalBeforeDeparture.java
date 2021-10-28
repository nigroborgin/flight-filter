package com.gridnine.testing;

import java.time.LocalDateTime;

public class ArrivalBeforeDeparture implements FlightFilter {
    @Override
    public boolean check(Flight flight) {
        if (flight.getSegments().size() == 1) {

            LocalDateTime departure = flight.getSegments().get(0).getDepartureDate();
            LocalDateTime arrival = flight.getSegments().get(0).getArrivalDate();
            return arrival.isBefore(departure);

        } else {
            for (int i = 0; i < flight.getSegments().size() - 1; i++) {

                LocalDateTime departure = flight.getSegments().get(i).getDepartureDate();
                LocalDateTime arrival = flight.getSegments().get(i).getArrivalDate();
                LocalDateTime departureNextSegment = flight.getSegments().get(i+1).getDepartureDate();

                if (arrival.isBefore(departure) || departureNextSegment.isBefore(arrival)) {
                    return true;
                }
            }
        }
        return false;
    }
}
