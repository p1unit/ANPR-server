package com.licenseplatedetector.licenseplatedetector.service;

import com.licenseplatedetector.licenseplatedetector.exception.ResourceNotFoundException;
import com.licenseplatedetector.licenseplatedetector.model.Vehicle;
import org.springframework.http.ResponseEntity;

public interface VehicleService {

    ResponseEntity addVehicle(Vehicle  vehicle);
    ResponseEntity updateVehicle(Vehicle  vehicle,String licenseNumber) throws ResourceNotFoundException;
    ResponseEntity getBasicInfo();
}
