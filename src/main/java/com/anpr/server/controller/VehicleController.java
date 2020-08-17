package com.anpr.server.controller;


import com.anpr.server.exception.CustomMessage;
import com.anpr.server.exception.ResourceNotFoundException;
import com.anpr.server.model.NumberPlateUrl;
import com.anpr.server.model.Vehicle;
import com.anpr.server.model.VehicleType;
import com.anpr.server.repository.VehicleRepository;
import com.anpr.server.resorces.EndPoints;
import com.anpr.server.resorces.Messages;
import com.anpr.server.service.PendingVehicleService;
import com.anpr.server.service.VehicleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1.1")
@Data
@AllArgsConstructor
public class VehicleController {

    private final VehicleRepository vehicleRepository;
    private final VehicleService vehicleService;
    private  final PendingVehicleService pendingVehicleService;

    @PostMapping(EndPoints.ADD_VEHICLE)
    public ResponseEntity<?> addVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @GetMapping(EndPoints.BASIC_INFORMATION)
    public @ResponseBody ResponseEntity<?> getBasicInfo(){
        return vehicleService.getBasicInfo();
    }

    @GetMapping(EndPoints.VEHICLE_ACTIVITY)
    public ResponseEntity<?> getVehicleActivity(@PathVariable(value = "licenseNumber") String licenseNumber,
                                             @RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                             @RequestParam(value = "endDate",required = false ) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                                @RequestParam(value = "page", required = false,defaultValue = "0") Integer page){

        return vehicleService.getVehicleDetails(licenseNumber,startDate,endDate);
    }

    @GetMapping(EndPoints.ALL_VEHICLE)
    public ResponseEntity<?> getAllVehiclesDetails(@RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                  @RequestParam(value = "endDate",required = false ) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate ,
                                                  @RequestParam(value = "inside",required = false) Boolean inside ,
                                                  @RequestParam(value = "vehicleType",required = false) String vehicleType){

        return vehicleService.getAllVehiclesDetails(startDate,endDate,inside,
                vehicleType==null ? null : VehicleType.valueOf(vehicleType));

    }

    @PostMapping("/addOrUpdate")
    public @ResponseBody ResponseEntity<?> vehicleEntered(@Valid @RequestBody NumberPlateUrl plateUrl){
        System.out.println(plateUrl.getUrl());
//        return ResponseEntity.ok().body("");
       return pendingVehicleService.addOrUpdateVehicle(plateUrl);
    }

    @GetMapping("/getAllPending")
    public @ResponseBody ResponseEntity<?> getAllPending(){
        return pendingVehicleService.getAllPendingVehicles();
    }

}