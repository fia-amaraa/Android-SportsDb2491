package com.praktek.sportsdb2491.api;

import com.praktek.sportsdb2491.model.EventsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceLast {
    @GET("eventspastleague.php?id=4328")
    Call<EventsResponse> getLastEvents(@Query("api_key") String apiKey);
}
