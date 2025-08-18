package com.inventory.sensorservice.service;

import com.inventory.sensorservice.model.SensorReading;
import com.inventory.sensorservice.model.SensorStatus;
import com.inventory.sensorservice.repository.SensorReadingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SensorService {
    
    private final SensorReadingRepository sensorReadingRepository;
    
    public SensorService(SensorReadingRepository sensorReadingRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
    }
    
    public SensorReading saveSensorReading(SensorReading sensorReading) {
        return sensorReadingRepository.save(sensorReading);
    }
    
    public List<SensorReading> getAllSensorReadings() {
        return sensorReadingRepository.findAll();
    }
    
    public Optional<SensorReading> getSensorReadingById(Long id) {
        return sensorReadingRepository.findById(id);
    }
    
    public List<SensorReading> getSensorReadingsBySensorId(String sensorId) {
        return sensorReadingRepository.findBySensorId(sensorId);
    }
    
    public List<SensorReading> getSensorReadingsByProductId(String productId) {
        return sensorReadingRepository.findByProductId(productId);
    }
    
    public List<SensorReading> getSensorReadingsByLocation(String location) {
        return sensorReadingRepository.findByLocation(location);
    }
    
    public List<SensorReading> getSensorReadingsByStatus(SensorStatus status) {
        return sensorReadingRepository.findByStatus(status);
    }
    
    public List<SensorReading> getSensorReadingsByDateRange(LocalDateTime start, LocalDateTime end) {
        return sensorReadingRepository.findByTimestampBetween(start, end);
    }
    
    public List<SensorReading> getLatestReadingsBySensorId(String sensorId) {
        return sensorReadingRepository.findLatestReadingsBySensorId(sensorId);
    }
    
    public List<SensorReading> getLowStockReadings(Integer threshold) {
        return sensorReadingRepository.findLowStockReadings(threshold);
    }
    
    public List<String> getAllUniqueSensorIds() {
        return sensorReadingRepository.findAllUniqueSensorIds();
    }
    
    public void deleteSensorReading(Long id) {
        sensorReadingRepository.deleteById(id);
    }
    
    public SensorReading updateSensorReading(Long id, SensorReading updatedReading) {
        return sensorReadingRepository.findById(id)
                .map(reading -> {
                    reading.setSensorId(updatedReading.getSensorId());
                    reading.setProductId(updatedReading.getProductId());
                    reading.setLocation(updatedReading.getLocation());
                    reading.setQuantity(updatedReading.getQuantity());
                    reading.setStatus(updatedReading.getStatus());
                    reading.setTemperature(updatedReading.getTemperature());
                    reading.setHumidity(updatedReading.getHumidity());
                    return sensorReadingRepository.save(reading);
                })
                .orElseThrow(() -> new RuntimeException("Sensor reading not found with id: " + id));
    }
    
    public long getTotalSensorReadingsCount() {
        return sensorReadingRepository.count();
    }
}