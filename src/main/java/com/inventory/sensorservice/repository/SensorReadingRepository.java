package com.inventory.sensorservice.repository;

import com.inventory.sensorservice.model.SensorReading;
import com.inventory.sensorservice.model.SensorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
    
    List<SensorReading> findBySensorId(String sensorId);
    
    List<SensorReading> findByProductId(String productId);
    
    List<SensorReading> findByLocation(String location);
    
    List<SensorReading> findByStatus(SensorStatus status);
    
    List<SensorReading> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT sr FROM SensorReading sr WHERE sr.sensorId = :sensorId ORDER BY sr.timestamp DESC")
    List<SensorReading> findLatestReadingsBySensorId(@Param("sensorId") String sensorId);
    
    @Query("SELECT sr FROM SensorReading sr WHERE sr.quantity <= :threshold")
    List<SensorReading> findLowStockReadings(@Param("threshold") Integer threshold);
    
    @Query("SELECT DISTINCT sr.sensorId FROM SensorReading sr")
    List<String> findAllUniqueSensorIds();
}