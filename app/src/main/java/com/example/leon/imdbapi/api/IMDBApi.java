package com.example.leon.imdbapi.api;

import com.example.leon.imdbapi.content.Movie;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Leon on 17.12.2015.
 */
public interface IMDBApi {

    @GET("/")
    Call<Movie> getMovie(@Query("t") String movie, @Query("plot") String plot);

}
