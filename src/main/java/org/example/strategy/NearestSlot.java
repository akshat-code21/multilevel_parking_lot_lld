package org.example.strategy;

import org.example.enums.SlotType;
import org.example.model.Gate;
import org.example.model.ParkingLevel;
import org.example.model.ParkingSlot;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class NearestSlot implements SlotStrategy {

    @Override
    public ParkingSlot findSlot(List<ParkingLevel> levels, SlotType slotType, Gate entryGate) {
        TreeSet<ParkingSlot> sortedSlots = new TreeSet<>(
                Comparator.comparingDouble((ParkingSlot slot) ->
                                slot.getDistanceToGate().getOrDefault(entryGate, Double.MAX_VALUE))
                        .thenComparing(ParkingSlot::getSlotId)
        );

        for (ParkingLevel level : levels) {
            List<ParkingSlot> slotsOfType = level.getSlotMapping().get(slotType);
            if (slotsOfType == null) continue;

            for (ParkingSlot slot : slotsOfType) {
                if (!slot.isOccupied()) {
                    sortedSlots.add(slot);
                }
            }
        }

        if (sortedSlots.isEmpty()) {
            return null;
        }

        return sortedSlots.first();
    }
}
