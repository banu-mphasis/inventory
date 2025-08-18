package com.inventory.sensorservice.controller;

import com.inventory.sensorservice.model.SensorReading;
import com.inventory.sensorservice.model.SensorStatus;
import com.inventory.sensorservice.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@Tag(name = "Sensor Controller", description = "API for managing inventory sensor readings")
public class SensorController {
    
    private final SensorService sensorService;
    
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }
    
    @PostMapping("/readings")
    @Operation(summary = "Create a new sensor reading")
    public ResponseEntity<SensorReading> createSensorReading(@Valid @RequestBody SensorReading sensorReading) {
        SensorReading savedReading = sensorService.saveSensorReading(sensorReading);
        return new ResponseEntity<>(savedReading, HttpStatus.CREATED);
    }
    
    @GetMapping("/readings")
    @Operation(summary = "Get all sensor readings")
    public ResponseEntity<List<SensorReading>> getAllSensorReadings() {
        List<SensorReading> readings = sensorService.getAllSensorReadings();
        return ResponseEntity.ok(readings);
    }
    
    @GetMapping("/readings/{id}")
    @Operation(summary = "Get sensor reading by ID")
    public ResponseEntity<SensorReading> getSensorReadingById(@PathVariable Long id) {
        return sensorService.getSensorReadingById(id)
                .map(reading -> ResponseEntity.ok(reading))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/readings/sensor/{sensorId}")
    @Operation(summary = "Get readings by sensor ID")
    public ResponseEntity<List<SensorReading>> getReadingsBySensorId(@PathVariable String sensorId) {
        List<SensorReading> readings = sensorService.getSensorReadingsBySensorId(sensorId);
        return ResponseEntity.ok(readings);
    }
    
    @GetMapping("/readings/product/{productId}")
    @Operation(summary = "Get readings by product ID")
    public ResponseEntity<List<SensorReading>> getReadingsByProductId(@PathVariable String productId) {
        List<SensorReading> readings = sensorService.getSensorReadingsByProductId(productId);
        return ResponseEntity.ok(readings);
    }
    
    @GetMapping("/readings/location/{location}")
    @Operation(summary = "Get readings by location")
    public ResponseEntity<List<SensorReading>> getReadingsByLocation(@PathVariable String location) {
        List<SensorReading> readings = sensorService.getSensorReadingsByLocation(location);
        return ResponseEntity.ok(readings);
    }
    
    @GetMapping("/readings/status/{status}")
    @Operation(summary = "Get readings by sensor status")
    public ResponseEntity<List<SensorReading>> getReadingsByStatus(@PathVariable SensorStatus status) {
        List<SensorReading> readings = sensorService.getSensorReadingsByStatus(status);
        return ResponseEntity.ok(readings);
    }
    
    @GetMapping("/readings/date-range")
    @Operation(summary = "Get readings within a date range")
    public ResponseEntity<List<SensorReading>> getReadingsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        List<SensorReading> readings = sensorService.getSensorReadingsByDateRange(start, end);
        return ResponseEntity.ok(readings);
    }
    
    @GetMapping("/readings/latest/{sensorId}")
    @Operation(summary = "Get latest readings for a sensor")
    public ResponseEntity<List<SensorReading>> getLatestReadingsBySensorId(@PathVariable String sensorId) {
        List<SensorReading> readings = sensorService.getLatestReadingsBySensorId(sensorId);
        return ResponseEntity.ok(readings);
    }
    
    @GetMapping("/readings/low-stock")
    @Operation(summary = "Get readings with low stock levels")
    public ResponseEntity<List<SensorReading>> getLowStockReadings(@RequestParam Integer threshold) {
        List<SensorReading> readings = sensorService.getLowStockReadings(threshold);
        return ResponseEntity.ok(readings);
    }
    
    @GetMapping("/ids")
    @Operation(summary = "Get all unique sensor IDs")
    public ResponseEntity<List<String>> getAllUniqueSensorIds() {
        List<String> sensorIds = sensorService.getAllUniqueSensorIds();
        return ResponseEntity.ok(sensorIds);
    }
    
    @PutMapping("/readings/{id}")
    @Operation(summary = "Update a sensor reading")
    public ResponseEntity<SensorReading> updateSensorReading(
            @PathVariable Long id, 
            @Valid @RequestBody SensorReading sensorReading) {
        try {
            SensorReading updatedReading = sensorService.updateSensorReading(id, sensorReading);
            return ResponseEntity.ok(updatedReading);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/readings/{id}")
    @Operation(summary = "Delete a sensor reading")
    public ResponseEntity<Void> deleteSensorReading(@PathVariable Long id) {
        if (sensorService.getSensorReadingById(id).isPresent()) {
            sensorService.deleteSensorReading(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/readings/count")
    @Operation(summary = "Get total count of sensor readings")
    public ResponseEntity<Long> getTotalReadingsCount() {
        long count = sensorService.getTotalSensorReadingsCount();
        return ResponseEntity.ok(count);
    }
}