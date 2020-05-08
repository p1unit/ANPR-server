package com.anpr.server.repository;

import com.anpr.server.model.Vehicle;
import com.anpr.server.model.VehicleTypeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findVehicleByLicenseNumberAndInsideTrue(String licenseNumber);

    @Query("SELECT new com.anpr.server.model.VehicleTypeCount(COUNT(v.vehicleType) ,v.vehicleType ) FROM Vehicle v WHERE v.inTime>?1 GROUP BY v.vehicleType")
    List<VehicleTypeCount> getAllTypes(Date date);

    @Query("SELECT new com.anpr.server.model.VehicleTypeCount(COUNT(v.vehicleType) ,v.vehicleType )" +
            " FROM Vehicle v WHERE v.inside = true GROUP BY v.vehicleType")
    List<VehicleTypeCount> getAllTypesCurrentlyInside();

    int countVehicleByInsideTrue();

    int countVehiclesByInTimeAfter(Date date);

    @Query(value = "SELECT all_vehicle.license_number FROM all_vehicle WHERE all_vehicle.in_time>=now() - INTERVAL 3 DAY GROUP BY all_vehicle.license_number\n" +
            "ORDER BY COUNT(all_vehicle.license_number) ASC LIMIT 10;", nativeQuery = true)
    List<String> mostVisitedfromDate(Date date);

    List<Vehicle> getByLicenseNumberAndInTimeIsBetweenOrOutTimeBetweenOrderByInTime(String str,Date start,Date end,Date start2,Date end2);
}
