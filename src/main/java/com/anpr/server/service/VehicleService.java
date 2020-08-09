package com.anpr.server.service;

import com.anpr.server.exception.ResourceNotFoundException;
import com.anpr.server.model.Vehicle;
import com.anpr.server.model.VehicleType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface VehicleService {

    ResponseEntity<?> addVehicle(Vehicle vehicle);
    ResponseEntity<?> updateVehicle(Vehicle  vehicle,String licenseNumber) throws ResourceNotFoundException;
    ResponseEntity<?> getBasicInfo();
    ResponseEntity<?> getVehicleDetails(String licenseNumber, LocalDateTime startDate, LocalDateTime endDate);
    ResponseEntity<?> getAllVehiclesDetails(LocalDateTime startDate, LocalDateTime endDate, Boolean isInside, VehicleType valueOf);
}
