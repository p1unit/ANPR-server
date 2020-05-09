package com.anpr.server.controller;


import com.anpr.server.exception.ResourceNotFoundException;
import com.anpr.server.model.Vehicle;
import com.anpr.server.model.VehicleType;
import com.anpr.server.repository.VehicleRepository;
import com.anpr.server.resorces.EndPoints;
import com.anpr.server.service.VehicleService;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1.1")
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    private final VehicleService vehicleService;

    public VehicleController(VehicleRepository vehicleRepository, VehicleService vehicleService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleService = vehicleService;
    }

    @PostMapping(EndPoints.ADD_VEHICLE)
    public ResponseEntity<?> addVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @PutMapping(EndPoints.UPDATE_STATUS)
    public ResponseEntity<?> updateVehicle(
            @PathVariable(value = "licenseNumber") String licenseNumber,
            @Valid @RequestBody Vehicle vehicleDetails) throws ResourceNotFoundException {

        return vehicleService.updateVehicle(vehicleDetails,licenseNumber);
    }

    @GetMapping(EndPoints.BASIC_INFORMATION)
    public ResponseEntity<?> getBasicInfo(){
        return vehicleService.getBasicInfo();
    }

    @GetMapping(EndPoints.VEHICLE_ACTIVITY)
    public ResponseEntity<?> getVehicleActivity(@PathVariable(value = "licenseNumber") String licenseNumber,
                                             @RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                             @RequestParam(value = "endDate",required = false ) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                                                @RequestParam(value = "page", required = false,defaultValue = "0") Integer page){

        return vehicleService.getVehicleDetails(licenseNumber,startDate,endDate,page);
    }

    @GetMapping(EndPoints.ALL_VEHICLE)
    public ResponseEntity<?> getAllVehiclesDetails(@RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                  @RequestParam(value = "endDate",required = false ) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate ,
                                                  @RequestParam(value = "isInside",required = false) Boolean isInside ,
                                                  @RequestParam(value = "vehicleType",required = false) String vehicleType){

        return vehicleService.getAllVehiclesDetails(startDate,endDate,isInside, VehicleType.valueOf(vehicleType));

    }


}