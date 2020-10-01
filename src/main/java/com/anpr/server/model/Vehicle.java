package com.anpr.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "all_vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vehicle {
    @Id
    private long id;

    private String licenseNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime inTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
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