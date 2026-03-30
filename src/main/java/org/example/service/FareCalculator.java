package org.example.service;

import org.example.enums.SlotType;
import org.example.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class FareCalculator {
    private final Map<SlotType, Double> priceMap;

    public FareCalculator(Map<SlotType, Double> priceMap) {
        this.priceMap = priceMap;
    }

    public double calculate(Ticket ticket, LocalDateTime exitTime) {
        double hourlyRate = priceMap.getOrDefault(ticket.getAssignedSlot().getSlotType(), 0.0);

        long totalMinutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        long hours = (long) Math.ceil(totalMinutes / 60.0);

        if (hours <= 0) {
            hours = 1;
        }

        return hours * hourlyRate;
    }

    public Map<SlotType, Double> getPriceMap() {
        return priceMap;
    }
}
