package org.example.model;

import org.example.enums.SlotType;

import java.util.List;
import java.util.Map;

public class ParkingLevel {
    private String levelId;
    private Map<SlotType, List<ParkingSlot>> slotMapping;

    public ParkingLevel(String levelId, Map<SlotType, List<ParkingSlot>> slotMapping) {
        this.levelId = levelId;
        this.slotMapping = slotMapping;
    }

    public String getLevelId() {
        return levelId;
    }

    public Map<SlotType, List<ParkingSlot>> getSlotMapping() {
        return slotMapping;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Level: ").append(levelId).append("\n");
        for (Map.Entry<SlotType, List<ParkingSlot>> entry : slotMapping.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(":\n");
            for (ParkingSlot slot : entry.getValue()) {
                sb.append("    ").append(slot).append("\n");
            }
        }
        return sb.toString();
    }
}
