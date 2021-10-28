package com.gridnine.testing;

import com.gridnine.testing.filter.ArrivalBeforeDeparture;
import com.gridnine.testing.filter.DepartureBeforeNow;
import com.gridnine.testing.filter.MoreTwoHoursOnGround;
import com.gridnine.testing.flights.Flight;
import com.gridnine.testing.flights.FlightBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        ArrivalBeforeDeparture filterArrivalBeforeDeparture = new ArrivalBeforeDeparture();
        DepartureBeforeNow filterDepartureBeforeNow = new DepartureBeforeNow();
        MoreTwoHoursOnGround filterMoreTwoHoursOnGround = new MoreTwoHoursOnGround();

        System.out.println("All:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
        System.out.println();
        System.out.println();

        for (Flight flight : flights) {
            if (!filterArrivalBeforeDeparture.check(flight)) {
                System.out.println("this flight went through a filter \"Departure Before Now\"");
                System.out.println(flight + "\n");
            }
        }
        System.out.println();
        System.out.println();

        for (Flight flight : flights) {
            if (!filterDepartureBeforeNow.check(flight)) {
                System.out.println("this flight went through a filter \"Arrival Before Departure\"");
                System.out.println(flight + "\n");
            }
        }
        System.out.println();
        System.out.println();

        for (Flight flight : flights) {
            if (!filterMoreTwoHoursOnGround.check(flight)) {
                System.out.println("this flight went through a filter \"More Two Hours On Ground\"");
                System.out.println(flight + "\n");
            }
        }
    }
}
