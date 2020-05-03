package com.licenseplatedetector.licenseplatedetector.service;

import com.licenseplatedetector.licenseplatedetector.exception.ResourceNotFoundException;
import com.licenseplatedetector.licenseplatedetector.model.BasicInfoModel;
import com.licenseplatedetector.licenseplatedetector.model.Vehicle;
import com.licenseplatedetector.licenseplatedetector.repository.VehicleRepository;
import com.licenseplatedetector.licenseplatedetector.validator.VehicleDetailsValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleDetailsValidator vehicleDetailsValidator;
    @Autowired
    VehicleRepository vehicleRepository;

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Override
    public ResponseEntity addVehicle(Vehicle vehicle) {

        logger.info("Request To add "+vehicle.toString());
        return vehicleDetailsValidator.validateAndSave(vehicle);
    }

    @Override
    public ResponseEntity updateVehicle(Vehicle vehicle, String licenseNumber) throws ResourceNotFoundException {
        return vehicleDetailsValidator.validateAndUpdate(vehicle,licenseNumber);
    }

    @Override
    public ResponseEntity getBasicInfo() {

        BasicInfoModel basicInfoModel=new BasicInfoModel();

        Date yesterday = new Date(System.currentTimeMillis()- (long) 8.64E7);
        Date lastweek =  new Date(System.currentTimeMillis()-7L*(long) 8.64E7);

        basicInfoModel.setCurrentlyInside(vehicleRepository.countVehicleByInsideTrue());
        basicInfoModel.setTodaysTotalCount(vehicleRepository.countVehiclesByInTimeAfter(yesterday));
        basicInfoModel.setMostFrequent(vehicleRepository.mostVisitedfromDate(lastweek));
        basicInfoModel.setTypeCountList(vehicleRepository.getallTypes(lastweek));

        return ResponseEntity.ok().body(basicInfoModel);
    }
}