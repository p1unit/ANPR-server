package com.anpr.server.resorces;

import java.time.ZoneId;

public class Resources {

    public static int PAGE_SIZE = 50;
    public static String BlobContainer = System.getenv("container");
    public static String VISION_KEY = System.getenv("vision-key");
    public static ZoneId indianZone = ZoneId.of("Asia/Kolkata");
    public static String VISION_URL = "https://eastus.api.cognitive.microsoft.com/vision/v3.0/";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String OPERATION_SUCCESSFUL = "Successful";
    public static final String USER_LOGGED_OUT = "User logged out successfully";
    public static final String PERMISSION_DENIED  = "Permission Denied or you can not edit this value";
    public static final String USER_CREATED = "User created please login with the credentials";
    public static final String DUPLICATE_USER = "User already present";
    public static final String PASSWORD_LENGTH_NOT_MATCHED = "Password length should be in between 8 - 12 ";
    public static final String CONFIRM_PASSWORD_ERROR = "Confirm Password not matched";
    public static final String USERNAME_IS_LARGE = "Username should be less than 32 Characters";
}