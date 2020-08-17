package com.anpr.server.service;

import com.anpr.server.model.NumberPlateUrl;
import org.springframework.http.ResponseEntity;

public interface PendingVehicleService {

    ResponseEntity<?> addOrUpdateVehicle(NumberPlateUrl url);

    ResponseEntity<?> getAllPendingVehicles();
}
