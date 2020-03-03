package com.licenseplatedetector.licenseplatedetector.controller;


import com.licenseplatedetector.licenseplatedetector.exception.ResourceNotFoundException;
import com.licenseplatedetector.licenseplatedetector.model.BasicInfoModel;
import com.licenseplatedetector.licenseplatedetector.model.Vehicle;
import com.licenseplatedetector.licenseplatedetector.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1.1")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/allVehicles")
    public List<Vehicle> getallVehicle(){
        return vehicleRepository.findAll();
    }

    @PostMapping("/addVehicle")
    public Vehicle addVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @PutMapping("/changeVehicleStatus/{licenseNumber}")
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

    @GetMapping("/basicinfo")
    public BasicInfoModel getBasicInfo(){

        BasicInfoModel basicInfoModel=new BasicInfoModel();


        Date yesterday = new Date(System.currentTimeMillis()- (long) 8.64E7);
        Date lastweek =  new Date(System.currentTimeMillis()-7L*(long) 8.64E7);

        basicInfoModel.setCurrentlyInside(vehicleRepository.currentlyInside());

        basicInfoModel.setTodaysTotalCount(vehicleRepository.todayVisited(yesterday));

        basicInfoModel.setMostFrequent(vehicleRepository.mostVisitedfromDate(lastweek));

        basicInfoModel.setTypeCountList(vehicleRepository.getallTypes(lastweek));
        return basicInfoModel;
    }

//    @GetMapping("/intialData")
//    public  getIntialData(){
//
//    }
}