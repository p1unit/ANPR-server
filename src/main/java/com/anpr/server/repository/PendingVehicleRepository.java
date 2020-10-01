package com.anpr.server.repository;

import com.anpr.server.model.PendingVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PendingVehicleRepository extends JpaRepository<PendingVehicle, Long> {

    PendingVehicle findPendingVehicleById(long id);

}
