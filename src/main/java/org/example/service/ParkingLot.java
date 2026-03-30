package org.example.service;

import org.example.enums.SlotType;
import org.example.enums.VehicleType;
import org.example.model.*;
import org.example.strategy.SlotStrategy;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {

    private static ParkingLot instance;

    private final List<ParkingLevel> levels;
    private final List<Gate> gates;
    private final FareCalculator calculator;
    private final SlotStrategy slotStrategy;

    private ParkingLot(List<ParkingLevel> levels, List<Gate> gates,
                       FareCalculator calculator, SlotStrategy slotStrategy) {
        this.levels = levels;
        this.gates = gates;
        this.calculator = calculator;
        this.slotStrategy = slotStrategy;
    }

    public static ParkingLot create(List<ParkingLevel> levels, List<Gate> gates,
                                    FareCalculator calculator, SlotStrategy slotStrategy) {
        if(instance == null){
            synchronized (ParkingLot.class){
                if(instance == null){
                    instance = new ParkingLot(levels, gates, calculator, slotStrategy);
                }
            }
        }
        return instance;
    }

    public static ParkingLot getInstance() {
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public Ticket park(Vehicle vehicle, LocalDateTime entryTime, int entryGate, SlotType slotType) {
        if (entryGate < 0 || entryGate >= gates.size()) {
            throw new IllegalArgumentException("Invalid entry gate index: " + entryGate);
        }

        Gate gate = gates.get(entryGate);

        synchronized (this){
            ParkingSlot slot = slotStrategy.findSlot(levels, slotType, gate);

            if (slot == null) {
                System.out.println("No available slot of type " + slotType + " found.");
                return null;
            }

            slot.occupy();

            String ticketId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            Ticket ticket = new Ticket(ticketId, vehicle.getVehicleType(), slot, gate, entryTime);
            System.out.println("Ticket issued: " + ticket);

            return ticket;
        }
    }

    public double exit(Ticket ticket, LocalDateTime exitTime) {
        ticket.getAssignedSlot().vacate();
        double fare = calculator.calculate(ticket, exitTime);
        System.out.println("Vehicle " + ticket.getVehicleType() + " exiting. Fare: ₹" + fare);
        return fare;
    }

    public String status() {
        StringBuilder sb = new StringBuilder();
        sb.append("======== PARKING LOT STATUS ========\n");

        for (ParkingLevel level : levels) {
            sb.append("Level: ").append(level.getLevelId()).append("\n");

            for (Map.Entry<SlotType, List<ParkingSlot>> entry : level.getSlotMapping().entrySet()) {
                SlotType type = entry.getKey();
                List<ParkingSlot> slots = entry.getValue();

                long total = slots.size();
                long occupied = slots.stream().filter(ParkingSlot::isOccupied).count();
                long available = total - occupied;

                sb.append("  ").append(type).append(" => Total: ").append(total)
                        .append(", Occupied: ").append(occupied)
                        .append(", Available: ").append(available).append("\n");

                for (ParkingSlot slot : slots) {
                    sb.append("    ").append(slot.getSlotId())
                            .append(" [").append(slot.isOccupied() ? "OCCUPIED" : "AVAILABLE").append("]\n");
                }
            }
        }

        sb.append("====================================\n");
        return sb.toString();
    }

    public List<ParkingLevel> getLevels() {
        return levels;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public FareCalculator getCalculator() {
        return calculator;
    }

    public SlotStrategy getSlotStrategy() {
        return slotStrategy;
    }
}
