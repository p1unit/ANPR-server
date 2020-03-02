package com.licenseplatedetector.licenseplatedetector.repository;

import com.licenseplatedetector.licenseplatedetector.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v from Vehicle v where v.licenseNumber = ?1 and v.inside = true ")
    Vehicle findByLicenseNumberAndInside(String licenseNumber);

    @Query("SELECT COUNT(v.vehicleType) AS Count ,v.vehicleType AS Type  FROM Vehicle v WHERE v.inTime<?1 GROUP BY v.vehicleType")
    List<String> getallTypes(Date date);
}
