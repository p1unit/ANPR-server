package com.anpr.server.service;

import com.anpr.server.model.Vehicle;
import com.anpr.server.model.VehicleType;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;


public interface VehicleService {

    ResponseEntity<?> addVehicle(Vehicle vehicle);
    ResponseEntity<?> getBasicInfo();
    ResponseEntity<?> getVehicleDetails(String licenseNumber, LocalDateTime startDate, LocalDateTime endDate);
    ResponseEntity<?> getAllVehiclesDetails(LocalDateTime startDate, LocalDateTime endDate, Boolean isInside, VehicleType valueOf);

}
