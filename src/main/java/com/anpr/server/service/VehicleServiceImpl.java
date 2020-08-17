package com.anpr.server.service;

import com.anpr.server.exception.CustomMessage;
import com.anpr.server.exception.ResourceNotFoundException;
import com.anpr.server.model.*;
import com.anpr.server.repository.VehicleRepository;
import com.anpr.server.resorces.Messages;
import com.anpr.server.resorces.Resources;
import com.anpr.server.validator.VehicleDetailsValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    public ResponseEntity getBasicInfo() {

        BasicInfoModel basicInfoModel=new BasicInfoModel();

        LocalDateTime yesterday = LocalDateTime.now(Resources.indianZone).minusDays(1L);
        LocalDateTime lastweek =  LocalDateTime.now(Resources.indianZone).minusDays(7L);
        LocalDateTime lastMonth =  LocalDateTime.now(Resources.indianZone).minusDays(30);

        logger.info(yesterday+" -> "+yesterday.toString());
        logger.info(yesterday+" -> "+lastweek.toString());

        basicInfoModel.setCurrentlyInside(vehicleRepository.countVehicleByInsideTrue());
        basicInfoModel.setTypeCountList(vehicleRepository.getAllTypes(lastweek));
        basicInfoModel.setInsideTypeCountList(vehicleRepository.getAllTypesCurrentlyInside());
        basicInfoModel.setLast1DayVisitor(vehicleRepository.countVehiclesByInTimeAfter(yesterday));
        basicInfoModel.setLast7DayVisitor(vehicleRepository.countVehiclesByInTimeAfter(lastweek));
        basicInfoModel.setLast30DayVisitor(vehicleRepository.countVehiclesByInTimeAfter(lastMonth));


        return ResponseEntity.ok().body(basicInfoModel);
    }

    @Override
    public ResponseEntity<?> getVehicleDetails(String licenseNumber, LocalDateTime startDate, LocalDateTime endDate) {

        if(startDate==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomMessage(Messages.START_DATE_EMPTY,HttpStatus.BAD_REQUEST));
        }

        endDate = endDate == null ? LocalDateTime.now(Resources.indianZone) : endDate;

        List<Vehicle> vehicleHistory = vehicleRepository.
                getByLicenseNumberAndInTimeIsBetweenOrOutTimeBetweenOrderByInTime(licenseNumber, startDate,
                        endDate,startDate,endDate);

        if(vehicleHistory.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomMessage(Messages.VEHICLE_NOT_FOUND+licenseNumber,HttpStatus.BAD_REQUEST));
        }

        return ResponseEntity.ok().body(new VehiclePage(vehicleHistory));
        
    }

    @Override
    public ResponseEntity<?> getAllVehiclesDetails(LocalDateTime startDate, LocalDateTime endDate, Boolean isInside, VehicleType vehicleType) {

        endDate = endDate == null ? LocalDateTime.now(Resources.indianZone) : endDate;

        if(startDate==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomMessage(Messages.START_DATE_EMPTY,HttpStatus.BAD_REQUEST));
        }

        List<Vehicle> vehicleHistory ;
        vehicleHistory = vehicleRepository.getAllDetails(startDate, endDate, vehicleType==null ? null :vehicleType.name(), isInside);

        if(vehicleHistory.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(Messages.NO_DATA_FOUND,HttpStatus.OK));
        }

        return ResponseEntity.ok().body(new VehiclePage(vehicleHistory));

    }



}