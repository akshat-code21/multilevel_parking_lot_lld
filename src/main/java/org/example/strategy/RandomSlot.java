package org.example.strategy;

import org.example.enums.SlotType;
import org.example.model.Gate;
import org.example.model.ParkingLevel;
import org.example.model.ParkingSlot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSlot implements SlotStrategy {

    private final Random random = new Random();

    @Override
    public ParkingSlot findSlot(List<ParkingLevel> levels, SlotType slotType, Gate entryGate) {
        List<ParkingSlot> availableSlots = new ArrayList<>();

        for (ParkingLevel level : levels) {
            List<ParkingSlot> slotsOfType = level.getSlotMapping().get(slotType);
            if (slotsOfType == null) continue;

            for (ParkingSlot slot : slotsOfType) {
                if (!slot.isOccupied()) {
                    availableSlots.add(slot);
                }
            }
        }

        if (availableSlots.isEmpty()) {
            return null;
        }

        return availableSlots.get(random.nextInt(availableSlots.size()));
    }
}
