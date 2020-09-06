package com.anpr.server.service;

import com.anpr.server.model.NumberPlateUrl;
import com.anpr.server.model.PendingVehicle;
import org.springframework.http.ResponseEntity;

public interface PendingVehicleService {

    ResponseEntity<?> addOrUpdateVehicle(NumberPlateUrl url);
    ResponseEntity<?> deletePendingVehicle(PendingVehicle pendingVehicle);
    ResponseEntity<?> getAllPendingVehicles();
}
