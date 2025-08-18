package com.inventory.sensorservice;

import com.inventory.sensorservice.model.SensorReading;
import com.inventory.sensorservice.model.SensorStatus;
import com.inventory.sensorservice.repository.SensorReadingRepository;
import com.inventory.sensorservice.service.SensorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class SensorServiceApplicationTests {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private SensorReadingRepository sensorReadingRepository;

    @Test
    void contextLoads() {
        assertNotNull(sensorService);
        assertNotNull(sensorReadingRepository);
    }

    @Test
    void testCreateSensorReading() {
        SensorReading reading = new SensorReading();
        reading.setSensorId("TEST-001");
        reading.setProductId("PROD-TEST");
        reading.setLocation("Test Location");
        reading.setQuantity(50);
        reading.setStatus(SensorStatus.ACTIVE);

        SensorReading saved = sensorService.saveSensorReading(reading);
        
        assertNotNull(saved.getId());
        assertEquals("TEST-001", saved.getSensorId());
        assertEquals("PROD-TEST", saved.getProductId());
        assertEquals(50, saved.getQuantity());
    }

    @Test
    void testFindBySensorId() {
        SensorReading reading = new SensorReading("TEST-002", "PROD-TEST2", "Test Location 2", 75);
        sensorService.saveSensorReading(reading);

        var readings = sensorService.getSensorReadingsBySensorId("TEST-002");
        
        assertFalse(readings.isEmpty());
        assertEquals("TEST-002", readings.get(0).getSensorId());
    }

    @Test
    void testLowStockReadings() {
        SensorReading lowStock = new SensorReading("TEST-003", "PROD-LOW", "Low Stock Location", 5);
        sensorService.saveSensorReading(lowStock);

        var lowStockReadings = sensorService.getLowStockReadings(10);
        
        assertTrue(lowStockReadings.stream()
                .anyMatch(r -> r.getSensorId().equals("TEST-003")));
    }
}