package com.gridnine.testing.filter;

import com.gridnine.testing.flights.Flight;
import com.gridnine.testing.flights.Segment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartureBeforeNowTest {

    DepartureBeforeNow departureBeforeNow = new DepartureBeforeNow();

    Flight flight;
    List<Segment> segments;

    void createFlightWithDepartureBeforeNow() {
        segments = new ArrayList<>();
        segments.add(new Segment(LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(3).plusHours(3)));
        flight = new Flight(segments);
    }

    void createFlightWithDepartureAfterNow() {
        segments = new ArrayList<>();
        segments.add(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(3)));
        flight = new Flight(segments);
    }

    @Test
    @DisplayName("departure before now")
    void checkDepartureBeforeNow() {
        createFlightWithDepartureBeforeNow();
        assertTrue(departureBeforeNow.check(flight));
    }

    @Test
    @DisplayName("departure after now")
    void checkDepartureAfterNow() {
        createFlightWithDepartureAfterNow();
        assertFalse(departureBeforeNow.check(flight));
    }
}
