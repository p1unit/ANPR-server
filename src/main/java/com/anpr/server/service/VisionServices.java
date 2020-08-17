package com.anpr.server.service;


import com.anpr.server.model.AllTexts;
import com.anpr.server.model.NumberPlateUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface VisionServices {

    @POST("ocr?language=unk&detectOrientation=true")
    Call<AllTexts> getTexts(@Body NumberPlateUrl plateUrl);
}
