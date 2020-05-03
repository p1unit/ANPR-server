package com.licenseplatedetector.licenseplatedetector.validator;

import com.licenseplatedetector.licenseplatedetector.exception.CustomMessage;
import com.licenseplatedetector.licenseplatedetector.exception.ResourceNotFoundException;
import com.licenseplatedetector.licenseplatedetector.model.Vehicle;
import com.licenseplatedetector.licenseplatedetector.repository.VehicleRepository;
import com.licenseplatedetector.licenseplatedetector.resorces.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class VehicleDetailsValidator {

    @Autowired
    private VehicleRepository vehicleRepository;

    public ResponseEntity validateAndSave(Vehicle vehicle){


        if(vehicle.getInImageUrl() ==null || vehicle.getVehicleType()==null
                || vehicle.getLicenseNumber()==null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new CustomMessage(Messages.MISSING_VALUES,HttpStatus.NOT_ACCEPTABLE));
        }

        vehicle.setInside(true);
        vehicle.setInTime(new Date());

        return ResponseEntity.ok().body(vehicleRepository.save(vehicle));

    }

    public ResponseEntity validateAndUpdate(Vehicle updatedValue,String licenseNumber) throws ResourceNotFoundException {

        Vehicle vehicle = vehicleRepository.findVehicleByLicenseNumberAndInsideTrue(licenseNumber);
        if(vehicle==null){
            throw new ResourceNotFoundException(Messages.VEHICLE_NOT_FOUND + licenseNumber);
        }
        vehicle.setInside(false);
        vehicle.setOutTime(new Date());
        vehicle.setOutImageUrl(updatedValue.getOutImageUrl());
        final Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity.ok(updatedVehicle);

    }
}