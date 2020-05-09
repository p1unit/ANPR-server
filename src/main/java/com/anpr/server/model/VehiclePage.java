package com.anpr.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehiclePage {

    int totalPages;
    int currentPage;
    List<Vehicle> pageData;
}