package com.anpr.server.service;

import com.anpr.server.model.*;
import com.anpr.server.repository.PendingVehicleRepository;
import com.anpr.server.repository.VehicleRepository;
import com.anpr.server.resorces.Resources;
import com.anpr.server.validator.VehicleDetailsValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import java.time.LocalDateTime;

@Service
@Slf4j
public class PendingVehicleIServiceImpl implements PendingVehicleService {

    @Autowired
    PendingVehicleRepository pendingvehicleRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    VehicleDetailsValidator vehicleDetailsValidator;

    private static final Logger logger = LoggerFactory.getLogger(PendingVehicleIServiceImpl.class);

    @Override
    public ResponseEntity<?> addOrUpdateVehicle(NumberPlateUrl url) {

        AllTexts allTexts = getAllTexts(url);
        if(allTexts ==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }

        logger.info(allTexts.toString());



        StringBuilder licensePlate = new StringBuilder();


        for(Region region:allTexts.getRegions()){
            for(Line line:region.getLines()){
                for(Word word:line.getWords()){
                    licensePlate.append(word.getText());
                }
            }
        }

        String[] parts = url.getUrl().split("/");
        Vehicle vehicle = vehicleRepository.findVehicleByLicenseNumberAndInsideTrue(licensePlate.toString());

        if(vehicle==null){
            PendingVehicle pVehicle = new PendingVehicle();
            pVehicle.setLicenseNumber(licensePlate.toString());
            pVehicle.setInside(true);
            pVehicle.setInImageUrl(Resources.BlobContainer+""+parts[parts.length-1]);
            pVehicle.setVehicleType(VehicleType.OTHER);
            pVehicle.setInTime(LocalDateTime.now());
            return ResponseEntity.ok().body(pendingvehicleRepository.save(pVehicle));
        }else {
            vehicle.setOutImageUrl(Resources.BlobContainer+""+parts[parts.length-1]);
            vehicle.setInside(false);
            vehicle.setOutTime(LocalDateTime.now(Resources.indianZone));

            return vehicleDetailsValidator.validateAndUpdate(vehicle);

        }
    }

    @Override
    public ResponseEntity<?> getAllPendingVehicles() {
        return ResponseEntity.ok().body(pendingvehicleRepository.findAll());
    }

    private AllTexts getAllTexts(NumberPlateUrl url){

        VisionServices visionServices = RestAPIService.createService(VisionServices.class);
        Call<AllTexts> callSync  = visionServices.getTexts(url);
        AllTexts allTexts=null;
        try{
            Response<AllTexts> response = callSync.execute();
            allTexts = response.body();

        }catch (Exception ex) { ex.printStackTrace(); }

        return allTexts;
    }
}