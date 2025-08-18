package com.inventory.sensorservice.model;

public enum SensorStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    MAINTENANCE("Under Maintenance"),
    ERROR("Error"),
    LOW_BATTERY("Low Battery");
    
    private final String description;
    
    SensorStatus(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}