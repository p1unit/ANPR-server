package com.anpr.server.resorces;

public class EndPoints {
    public static final String VEHICLE_ACTIVITY = "/vehicleDetails/{licenseNumber}";
    public static final String ALL_VEHICLE = "/allVehicle";
    public static final String BASIC_INFORMATION = "/basicInfo";
    public static final String UPDATE_STATUS = "/changeVehicleStatus/{licenseNumber}";
    public static final String ADD_VEHICLE = "/addVehicle";
    public static final String ADD_OR_UPDATE_NEW_VECHICLE = "/addOrUpdate";
    public static final String DELETE_VEHICLE = "/deleteVehicle";
    public static final String ALL_PENDING = "/getAllPending";
    public static final String USER_BASE = "/user";
    public static final String USER_HOME = "/";
    public static final String CURRENT_USER = "/currentUser";
    public static final String REGISTER_USER = "/register";
    public static final String FIND_USER = "/{userId}";
    public static final String LOG_OUT = "/logout";
}