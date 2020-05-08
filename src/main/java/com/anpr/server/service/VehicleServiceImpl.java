package com.anpr.server.service;

import com.anpr.server.exception.CustomMessage;
import com.anpr.server.exception.ResourceNotFoundException;
import com.anpr.server.model.BasicInfoModel;
import com.anpr.server.model.Vehicle;
import com.anpr.server.repository.VehicleRepository;
import com.anpr.server.resorces.Messages;
import com.anpr.server.validator.VehicleDetailsValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        basicInfoModel.setTypeCountList(vehicleRepository.getAllTypes(lastweek));
        basicInfoModel.setInsideTypeCountList(vehicleRepository.getAllTypesCurrentlyInside());

        return ResponseEntity.ok().body(basicInfoModel);
    }

    @Override
    public ResponseEntity getVehicleDetails(String licenseNumber,Date startDate,Date endDate) {

        if(startDate==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomMessage(Messages.START_DATE_EMPTY+licenseNumber,HttpStatus.BAD_REQUEST));
        }

        endDate = endDate == null ? new Date() : endDate;

        List<Vehicle> vehicleHistory = vehicleRepository.
                getByLicenseNumberAndInTimeIsBetweenOrOutTimeBetweenOrderByInTime(licenseNumber, startDate,
                        endDate,startDate,endDate);

        if(vehicleHistory.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomMessage(Messages.VEHICLE_NOT_FOUND+licenseNumber,HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok().body(vehicleHistory);
        
    }


}