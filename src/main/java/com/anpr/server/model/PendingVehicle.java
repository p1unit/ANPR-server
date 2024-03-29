package com.anpr.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "all_pending_vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PendingVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String licenseNumber;

    private LocalDateTime inTime;

    private LocalDateTime outTime;

    private String inImageUrl;

    private String outImageUrl;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private boolean inside;

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", inImageUrl='" + inImageUrl + '\'' +
                ", outImageUrl='" + outImageUrl + '\'' +
                ", vehicleType=" + vehicleType +
                ", inside=" + inside +
                '}';
    }
}