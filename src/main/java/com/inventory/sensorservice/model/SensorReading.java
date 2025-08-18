package com.inventory.sensorservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_readings")
public class SensorReading {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Sensor ID is required")
    @Column(name = "sensor_id", nullable = false)
    private String sensorId;
    
    @NotBlank(message = "Product ID is required")
    @Column(name = "product_id", nullable = false)
    private String productId;
    
    @NotBlank(message = "Location is required")
    @Column(name = "location", nullable = false)
    private String location;
    
    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity must be zero or positive")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SensorStatus status;
    
    @Column(name = "temperature")
    private Double temperature;
    
    @Column(name = "humidity")
    private Double humidity;
    
    // Constructors
    public SensorReading() {
        this.timestamp = LocalDateTime.now();
        this.status = SensorStatus.ACTIVE;
    }
    
    public SensorReading(String sensorId, String productId, String location, Integer quantity) {
        this();
        this.sensorId = sensorId;
        this.productId = productId;
        this.location = location;
        this.quantity = quantity;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSensorId() { return sensorId; }
    public void setSensorId(String sensorId) { this.sensorId = sensorId; }
    
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    public SensorStatus getStatus() { return status; }
    public void setStatus(SensorStatus status) { this.status = status; }
    
    public Double getTemperature() { return temperature; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
    
    public Double getHumidity() { return humidity; }
    public void setHumidity(Double humidity) { this.humidity = humidity; }
    
    @Override
    public String toString() {
        return "SensorReading{" +
                "id=" + id +
                ", sensorId='" + sensorId + '\'' +
                ", productId='" + productId + '\'' +
                ", location='" + location + '\'' +
                ", quantity=" + quantity +
                ", timestamp=" + timestamp +
                ", status=" + status +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }
}