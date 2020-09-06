package com.anpr.server.validator;

import com.anpr.server.exception.CustomMessage;
import com.anpr.server.exception.ResourceNotFoundException;
import com.anpr.server.model.PendingVehicle;
import com.anpr.server.model.Vehicle;
import com.anpr.server.repository.PendingVehicleRepository;
import com.anpr.server.repository.VehicleRepository;
import com.anpr.server.resorces.Messages;
import com.anpr.server.resorces.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class VehicleDetailsValidator {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PendingVehicleRepository pendingVehicleRepository;

    public ResponseEntity<?> validateAndSave(Vehicle vehicle){

        PendingVehicle pendingVehicle = pendingVehicleRepository.findPendingVehicleById(vehicle.getId());
        pendingVehicleRepository.delete(pendingVehicle);

        return ResponseEntity.ok().body(vehicleRepository.save(vehicle));

    }

    public ResponseEntity<?> validateAndUpdate(Vehicle vehicle) {

        final Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity.ok(updatedVehicle);

    }
}