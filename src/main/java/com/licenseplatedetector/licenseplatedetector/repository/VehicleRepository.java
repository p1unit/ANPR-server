package com.licenseplatedetector.licenseplatedetector.repository;

import com.licenseplatedetector.licenseplatedetector.model.Vehicle;
import com.licenseplatedetector.licenseplatedetector.model.VehicleTypeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v from Vehicle v where v.licenseNumber = ?1 and v.inside = true ")
    Vehicle findByLicenseNumberAndInside(String licenseNumber);

    @Query("SELECT new com.licenseplatedetector.licenseplatedetector.model.VehicleTypeCount(COUNT(v.vehicleType) ,v.vehicleType ) FROM Vehicle v WHERE v.inTime>?1 GROUP BY v.vehicleType")
    List<VehicleTypeCount> getallTypes(Date date);

    @Query("SELECT COUNT(v.id) FROM Vehicle v WHERE v.inside=true")
    int currentlyInside();

    @Query("SELECT COUNT(v.id) FROM Vehicle v WHERE v.inTime >= ?1")
    int todayVisited(Date date);

//    @Query(value = "SELECT v.license_number FROM Vehicle v WHERE v.in_time => now() - INTERVAL 7 DAY GROUP BY v.license_number ORDER BY COUNT (v.license_number) ASC LIMIT 10",nativeQuery = true)
//    List<String> mostVisitedfromDate(Date date);

    @Query(value = "SELECT all_vehicle.license_number FROM all_vehicle WHERE all_vehicle.in_time>=now() - INTERVAL 3 DAY GROUP BY all_vehicle.license_number\n" +
            "ORDER BY COUNT(all_vehicle.license_number) ASC LIMIT 10;", nativeQuery = true)
    List<String> mostVisitedfromDate(Date date);
}
