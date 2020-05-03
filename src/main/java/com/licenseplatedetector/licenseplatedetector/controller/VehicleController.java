package com.licenseplatedetector.licenseplatedetector.controller;


import com.licenseplatedetector.licenseplatedetector.exception.ResourceNotFoundException;
import com.licenseplatedetector.licenseplatedetector.model.BasicInfoModel;
import com.licenseplatedetector.licenseplatedetector.model.Vehicle;
import com.licenseplatedetector.licenseplatedetector.repository.VehicleRepository;
import com.licenseplatedetector.licenseplatedetector.service.VehicleService;
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

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/allVehicles")
    public List<Vehicle> getallVehicle(){
        return vehicleRepository.findAll();
    }

    @PostMapping("/addVehicle")
    public ResponseEntity addVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @PutMapping("/changeVehicleStatus/{licenseNumber}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable(value = "licenseNumber") String licenseNumber,
            @Valid @RequestBody Vehicle vehicleDetails) throws ResourceNotFoundException {

        return vehicleService.updateVehicle(vehicleDetails,licenseNumber);
    }

    @GetMapping("/basicinfo")
    public ResponseEntity getBasicInfo(){

        return vehicleService.getBasicInfo();
    }

}