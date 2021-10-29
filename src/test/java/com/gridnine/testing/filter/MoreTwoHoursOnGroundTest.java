package com.gridnine.testing.filter;

import com.gridnine.testing.flights.Flight;
import com.gridnine.testing.flights.Segment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoreTwoHoursOnGroundTest {

    MoreTwoHoursOnGround moreTwoHoursOnGround = new MoreTwoHoursOnGround();

    Flight flight;
    List<Segment> segments;

    void createFlightWithOneSegment() {
        segments = new ArrayList<>();
        segments.add(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(3)));
        flight = new Flight(segments);
    }

    void createFlightWithManySegmentsAndMoreTwoHours() {
        segments = new ArrayList<>();
        segments.add(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(3)));
        segments.add(new Segment(LocalDateTime.now().plusDays(3).plusHours(6), LocalDateTime.now().plusDays(3).plusHours(7)));
        flight = new Flight(segments);
    }

    void createFlightWithManySegmentsAndLessTwoHours() {
        segments = new ArrayList<>();
        segments.add(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(3)));
        segments.add(new Segment(LocalDateTime.now().plusDays(3).plusHours(4), LocalDateTime.now().plusDays(3).plusHours(7)));
        flight = new Flight(segments);
    }

    @Test
    @DisplayName("flight is one segment")
    void oneSegment() {
        createFlightWithOneSegment();
        assertFalse(moreTwoHoursOnGround.check(flight));
    }

    @Test
    @DisplayName("flight is many segments and time on ground is more two hours")
    void manySegmentsAndMoreTwoHours() {
        createFlightWithManySegmentsAndMoreTwoHours();
        assertTrue(moreTwoHoursOnGround.check(flight));
    }

    @Test
    @DisplayName("flight is many segments and time on ground is less two hours")
    void manySegmentsAndLessTwoHours() {
        createFlightWithManySegmentsAndLessTwoHours();
        assertFalse(moreTwoHoursOnGround.check(flight));
    }

}