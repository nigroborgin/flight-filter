package com.gridnine.testing.filter;

import com.gridnine.testing.flights.Flight;
import com.gridnine.testing.flights.Segment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ArrivalBeforeDepartureTest {

    ArrivalBeforeDeparture arrivalBeforeDeparture = new ArrivalBeforeDeparture();

    Flight flightValid;
    List<Segment> segmentsValid;
    Flight flightInvalid;
    List<Segment> segmentsInvalid;

    void createValidFlightOneSegment() {
        segmentsValid = new ArrayList<>();
        segmentsValid.add(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(3)));
        flightValid = new Flight(segmentsValid);
    }

    void createValidFlightManySegments() {
        segmentsValid = new ArrayList<>();
        segmentsValid.add(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(3)));
        segmentsValid.add(new Segment(LocalDateTime.now().plusDays(3).plusHours(4), LocalDateTime.now().plusDays(3).plusHours(5)));
        segmentsValid.add(new Segment(LocalDateTime.now().plusDays(3).plusHours(6), LocalDateTime.now().plusDays(3).plusHours(8)));
        flightValid = new Flight(segmentsValid);
    }

    void createInvalidFlightOneSegment() {
        segmentsInvalid = new ArrayList<>();
        segmentsInvalid.add(new Segment(LocalDateTime.now().plusDays(3).plusHours(3), LocalDateTime.now().plusDays(3)));
        flightInvalid = new Flight(segmentsInvalid);
    }

    void createInvalidFlightAllInvalidSegments() {
        segmentsInvalid = new ArrayList<>();
        segmentsInvalid.add(new Segment(LocalDateTime.now().plusDays(3).plusHours(3), LocalDateTime.now().plusDays(3)));
        segmentsInvalid.add(new Segment(LocalDateTime.now().plusDays(3).plusHours(5), LocalDateTime.now().plusDays(3).plusHours(4)));
        segmentsInvalid.add(new Segment(LocalDateTime.now().plusDays(3).plusHours(8), LocalDateTime.now().plusDays(3).plusHours(6)));
        flightInvalid = new Flight(segmentsInvalid);
    }

    void createInvalidFlightManySegmentsDepNextBeforeArrThis() {
        segmentsInvalid = new ArrayList<>();
        segmentsInvalid.add(new Segment(LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(3)));
        segmentsInvalid.add(new Segment(LocalDateTime.now().plusDays(3).plusHours(2), LocalDateTime.now().plusDays(3).plusHours(4)));
        segmentsInvalid.add(new Segment(LocalDateTime.now().plusDays(3).plusHours(6), LocalDateTime.now().plusDays(3).plusHours(8)));
    }

    @Test()
    @DisplayName("one segment and valid date")
    void checkOneAndValid() {
        createValidFlightOneSegment();
        assertFalse(arrivalBeforeDeparture.check(flightValid));
    }

    @Test()
    @DisplayName("one segment and invalid date")
    void checkOneAndInvalid() {
        createInvalidFlightOneSegment();
        assertTrue(arrivalBeforeDeparture.check(flightInvalid));
    }

    @Test()
    @DisplayName("many segments and all invalid dates")
    void checkManyInvalid() {
        createInvalidFlightAllInvalidSegments();
        assertTrue(arrivalBeforeDeparture.check(flightInvalid));
    }

    @Test()
    @DisplayName("many segments and contains departure next segment is before arrival this segment")
    void checkManyInvalidDepNextBeforeArrThis() {
        createInvalidFlightManySegmentsDepNextBeforeArrThis();
        assertTrue(arrivalBeforeDeparture.check(flightInvalid));
    }

    @Test
    @DisplayName("many segments and all valid dates")
    void checkManyValid() {
        createValidFlightManySegments();
        assertFalse(arrivalBeforeDeparture.check(flightValid));
    }
}
