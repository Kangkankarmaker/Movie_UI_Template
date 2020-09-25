package com.example.movieuitemplate.utilits;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Toast;

import com.example.movieuitemplate.ApiClient.apiclient;
import com.example.movieuitemplate.R;
import com.example.movieuitemplate.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    public static List<Movie>getPopularMovies(){


        ArrayList<Movie> lstMovies=new ArrayList<>();
        lstMovies.add(new Movie("Ready", R.drawable.avenger,R.drawable.avenger));
        lstMovies.add(new Movie("Avanger",R.drawable.avenger));
        lstMovies.add(new Movie("Ready",R.drawable.avenger));
        lstMovies.add(new Movie("Ready",R.drawable.avenger));
        lstMovies.add(new Movie("Ready",R.drawable.avenger));
        return lstMovies;
    }

     public static List<Movie>getWeekMovies(){

         ArrayList<Movie> lstMovies=new ArrayList<>();
         lstMovies.add(new Movie("Ready", R.drawable.avenger,R.drawable.avenger));
         lstMovies.add(new Movie("Avanger",R.drawable.avenger));
         lstMovies.add(new Movie("Ready",R.drawable.avenger));
         lstMovies.add(new Movie("Ready",R.drawable.avenger));
         lstMovies.add(new Movie("Ready",R.drawable.avenger));
         return lstMovies;

     }
}
