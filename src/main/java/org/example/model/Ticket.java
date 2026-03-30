package org.example.model;

import org.example.enums.VehicleType;

import java.time.LocalDateTime;

public class Ticket {
    private final String ticketId;
    private final VehicleType vehicleType;
    private final ParkingSlot assignedSlot;
    private final Gate entryGate;
    private final LocalDateTime entryTime;

    public Ticket(String ticketId, VehicleType vehicleType, ParkingSlot assignedSlot,
                  Gate entryGate, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicleType = vehicleType;
        this.assignedSlot = assignedSlot;
        this.entryGate = entryGate;
        this.entryTime = entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public ParkingSlot getAssignedSlot() {
        return assignedSlot;
    }

    public Gate getEntryGate() {
        return entryGate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", vehicleType=" + vehicleType +
                ", assignedSlot=" + assignedSlot +
                ", entryGate=" + entryGate +
                ", entryTime=" + entryTime +
                '}';
    }
}
