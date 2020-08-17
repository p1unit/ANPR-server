package com.anpr.server.resorces;

import java.time.ZoneId;

public class Resources {

    public static int PAGE_SIZE = 50;
    public static String BlobContainer = System.getenv("container");
    public static String VISION_KEY = System.getenv("vision-key");
    public static ZoneId indianZone = ZoneId.of("Asia/Kolkata");
    public static String VISION_URL = "https://eastus.api.cognitive.microsoft.com/vision/v3.0/";
}