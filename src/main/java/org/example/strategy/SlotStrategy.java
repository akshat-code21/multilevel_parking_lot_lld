package org.example.strategy;

import org.example.enums.SlotType;
import org.example.model.Gate;
import org.example.model.ParkingLevel;
import org.example.model.ParkingSlot;

import java.util.List;

public interface SlotStrategy {
    ParkingSlot findSlot(List<ParkingLevel> levels, SlotType slotType, Gate entryGate);
}
