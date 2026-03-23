package org.example.model;

import org.example.enums.SlotType;

import java.util.Map;

public class ParkingSlot {
    private String slotId;
    private SlotType slotType;
    private Map<Gate, Double> distanceToGate;
    private boolean isOccupied;

    public ParkingSlot(String slotId, SlotType slotType, Map<Gate, Double> distanceToGate) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.distanceToGate = distanceToGate;
        this.isOccupied = false;
    }

    public String getSlotId() {
        return slotId;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public Map<Gate, Double> getDistanceToGate() {
        return distanceToGate;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy() {
        this.isOccupied = true;
    }

    public void vacate() {
        this.isOccupied = false;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "slotId='" + slotId + '\'' +
                ", slotType=" + slotType +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
