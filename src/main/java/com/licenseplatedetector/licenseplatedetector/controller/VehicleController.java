package com.licenseplatedetector.licenseplatedetector.controller;


import com.licenseplatedetector.licenseplatedetector.exception.ResourceNotFoundException;
import com.licenseplatedetector.licenseplatedetector.model.Vehicle;
import com.licenseplatedetector.licenseplatedetector.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1.1")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/vehicle")
    public List<Vehicle> getallVehicle(){
        List str=vehicleRepository.getallTypes(new Date());
        System.out.println(str);
        return vehicleRepository.findAll();
    }

    @PostMapping("/vehicle")
    public Vehicle addVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @PutMapping("/vehicle/{licenseNumber}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable(value = "licenseNumber") String licenseNumber,
            @Valid @RequestBody Vehicle vehicleDetails) throws ResourceNotFoundException {

        Vehicle vehicle = vehicleRepository.findByLicenseNumberAndInside(licenseNumber);
        if(vehicle==null){
            throw new ResourceNotFoundException("Vehicle not found on :: " + licenseNumber);
        }
        vehicle.setInside(false);
        vehicle.setOutTime(vehicleDetails.getOutTime());
        final Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return ResponseEntity.ok(updatedVehicle);
    }

//    @GetMapping("/intialData")
//    public  getIntialData(){
//
//    }
}