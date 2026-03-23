package org.example;

import org.example.enums.SlotType;
import org.example.enums.VehicleType;
import org.example.model.*;
import org.example.service.FareCalculator;
import org.example.service.ParkingLot;
import org.example.strategy.NearestSlot;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Gate gate0 = new Gate("G0");
        Gate gate1 = new Gate("G1");
        List<Gate> gates = List.of(gate0, gate1);

        ParkingSlot s1 = new ParkingSlot("L1-S1", SlotType.SMALL, Map.of(gate0, 5.0, gate1, 15.0));
        ParkingSlot s2 = new ParkingSlot("L1-S2", SlotType.SMALL, Map.of(gate0, 10.0, gate1, 8.0));
        ParkingSlot m1 = new ParkingSlot("L1-M1", SlotType.MEDIUM, Map.of(gate0, 3.0, gate1, 20.0));
        ParkingSlot m2 = new ParkingSlot("L1-M2", SlotType.MEDIUM, Map.of(gate0, 12.0, gate1, 6.0));
        ParkingSlot l1 = new ParkingSlot("L1-L1", SlotType.LARGE, Map.of(gate0, 7.0, gate1, 10.0));

        Map<SlotType, List<ParkingSlot>> level1Slots = new HashMap<>();
        level1Slots.put(SlotType.SMALL, new ArrayList<>(List.of(s1, s2)));
        level1Slots.put(SlotType.MEDIUM, new ArrayList<>(List.of(m1, m2)));
        level1Slots.put(SlotType.LARGE, new ArrayList<>(List.of(l1)));

        ParkingLevel level1 = new ParkingLevel("L1", level1Slots);

        ParkingSlot s3 = new ParkingSlot("L2-S1", SlotType.SMALL, Map.of(gate0, 20.0, gate1, 3.0));
        ParkingSlot m3 = new ParkingSlot("L2-M1", SlotType.MEDIUM, Map.of(gate0, 18.0, gate1, 5.0));
        ParkingSlot l2 = new ParkingSlot("L2-L1", SlotType.LARGE, Map.of(gate0, 25.0, gate1, 2.0));

        Map<SlotType, List<ParkingSlot>> level2Slots = new HashMap<>();
        level2Slots.put(SlotType.SMALL, new ArrayList<>(List.of(s3)));
        level2Slots.put(SlotType.MEDIUM, new ArrayList<>(List.of(m3)));
        level2Slots.put(SlotType.LARGE, new ArrayList<>(List.of(l2)));

        ParkingLevel level2 = new ParkingLevel("L2", level2Slots);

        List<ParkingLevel> levels = List.of(level1, level2);

        Map<SlotType, Double> priceMap = Map.of(
                SlotType.SMALL, 10.0,
                SlotType.MEDIUM, 20.0,
                SlotType.LARGE, 30.0
        );
        FareCalculator calculator = new FareCalculator(priceMap);

        ParkingLot parkingLot = ParkingLot.create(levels, gates, calculator, new NearestSlot());

        System.out.println(parkingLot.status());

        Vehicle bike = new Vehicle("MH-12-AB-1234", VehicleType.TWO_WHEELER);
        Vehicle car = new Vehicle("MH-14-CD-5678", VehicleType.CAR);
        Vehicle bus = new Vehicle("MH-01-EF-9012", VehicleType.BUS);

        LocalDateTime now = LocalDateTime.now();

        Ticket bikeTicket = parkingLot.park(bike, now, 0, SlotType.SMALL);
        Ticket carTicket = parkingLot.park(car, now, 1, SlotType.MEDIUM);
        Ticket busTicket = parkingLot.park(bus, now, 0, SlotType.LARGE);

        System.out.println("\n--- After parking 3 vehicles ---");
        System.out.println(parkingLot.status());

        LocalDateTime carExitTime = now.plusHours(3);
        double carFare = parkingLot.exit(carTicket, carExitTime);
        System.out.println("Car fare for 3 hours: ₹" + carFare);

        System.out.println("\n--- After car exits ---");
        System.out.println(parkingLot.status());
    }
}