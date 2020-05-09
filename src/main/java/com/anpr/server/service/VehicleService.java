package com.anpr.server.service;

import com.anpr.server.exception.ResourceNotFoundException;
import com.anpr.server.model.Vehicle;
import com.anpr.server.model.VehicleType;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface VehicleService {

    ResponseEntity<?> addVehicle(Vehicle vehicle);
    ResponseEntity<?> updateVehicle(Vehicle  vehicle,String licenseNumber) throws ResourceNotFoundException;
    ResponseEntity<?> getBasicInfo();
    ResponseEntity<?> getVehicleDetails(String licenseNumber, Date startDate,Date endDate,int page);
    ResponseEntity<?> getAllVehiclesDetails(Date startDate, Date endDate, Boolean isInside, VehicleType valueOf);
}
