package com.anpr.server.repository;

import com.anpr.server.model.Vehicle;
import com.anpr.server.model.VehicleType;
import com.anpr.server.model.VehicleTypeCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findVehicleByLicenseNumberAndInsideTrue(String licenseNumber);

    @Query("SELECT new com.anpr.server.model.VehicleTypeCount(COUNT(v.vehicleType) ,v.vehicleType ) FROM Vehicle v WHERE v.inTime>?1 GROUP BY v.vehicleType")
    List<VehicleTypeCount> getAllTypes(LocalDateTime date);

    @Query("SELECT new com.anpr.server.model.VehicleTypeCount(COUNT(v.vehicleType) ,v.vehicleType )" +
            " FROM Vehicle v WHERE v.inside = true GROUP BY v.vehicleType")
    List<VehicleTypeCount> getAllTypesCurrentlyInside();

    int countVehicleByInsideTrue();

    int countVehiclesByInTimeAfter(LocalDateTime date);

    @Query(value = "SELECT all_vehicle.license_number FROM all_vehicle WHERE all_vehicle.in_time>=now() - INTERVAL 3 DAY GROUP BY all_vehicle.license_number\n" +
            "ORDER BY COUNT(all_vehicle.license_number) ASC LIMIT 10;", nativeQuery = true)
    List<String> mostVisitedfromDate(LocalDateTime date);

    List<Vehicle> getByLicenseNumberAndInTimeIsBetweenOrOutTimeBetweenOrderByInTime(String str, LocalDateTime start, LocalDateTime end, LocalDateTime start2, LocalDateTime end2);

    @Query("SELECT v FROM Vehicle v WHERE ( (v.outTime  >= ?1 AND v.outTime  <= ?2) OR ( v.inTime >= ?1 AND v.inTime <= ?2) ) " +
            "AND (?3 is NULL OR v.vehicleType = ?3) AND (?4 is NULL OR v.inside = ?4 ) " )
    List<Vehicle> getAllDetails(LocalDateTime statDate, LocalDateTime endDate, String type, Boolean inside);


//    @Query("SELECT v FROM Vehicle v WHERE ( (v.outTime  >= ?1 AND v.outTime  <= ?2) OR ( v.inTime >= ?1 AND v.inTime <= ?2) ) " +
//            "AND (?3 is NULL OR v.vehicleType = ?3) " )
//    List<Vehicle> getAllDetails(LocalDateTime statDate, LocalDateTime endDate, String type);

}
