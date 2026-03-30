package org.example.model;

public class Gate {
    private final String gateId;

    public Gate(String gateId) {
        this.gateId = gateId;
    }

    public String getGateId() {
        return gateId;
    }

    @Override
    public String toString() {
        return "Gate{" +
                "gateId='" + gateId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gate gate = (Gate) o;
        return gateId.equals(gate.gateId);
    }

    @Override
    public int hashCode() {
        return gateId.hashCode();
    }
}
