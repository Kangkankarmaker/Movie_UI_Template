package com.example.movieuitemplate;

import com.example.movieuitemplate.pojo.MovieRating;
import com.example.movieuitemplate.pojo.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface select  {

    @GET("selectRecent.php")
    Call<List<MovieResponse>> getPopular();

    @GET("selectAfter.php")
    Call<List<MovieResponse>> getAfter();

    @GET("selectSlider.php")
    Call<List<MovieResponse>> getSlider();

    @GET("selectMovieRating.php")
    Call<List<MovieRating>> getRate(
            @Query("movieid") String movieid
    );
}

