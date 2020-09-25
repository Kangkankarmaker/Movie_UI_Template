package com.example.movieuitemplate.adapters;

import android.widget.ImageView;

import com.example.movieuitemplate.pojo.MovieResponse;

public interface MovieItemClickListener {

    void onMovieClick(MovieResponse movie, ImageView movieImageView);

}
