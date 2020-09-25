package com.example.movieuitemplate.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.movieuitemplate.ApiClient.apiclient;
import com.example.movieuitemplate.Loading_Dialog;
import com.example.movieuitemplate.adapters.MovieItemClickListener;
import com.example.movieuitemplate.adapters.Movie_adapter;
import com.example.movieuitemplate.R;
import com.example.movieuitemplate.adapters.SliderPageAdapter;
import com.example.movieuitemplate.pojo.MovieResponse;
import com.example.movieuitemplate.select;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements MovieItemClickListener {

    ArrayList<MovieResponse> listSlide =new ArrayList<>();

    private ViewPager sliderPager;
    SliderPageAdapter sliderAdapter;

    private TabLayout indicator;

    private RecyclerView MovieRV,moviesRVweek;

    ImageView allMovie,pupularMovie;

    ArrayList<MovieResponse> respons =new ArrayList<>();
    Movie_adapter adapter;
    Loading_Dialog loading_dialog=new Loading_Dialog(HomeActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        initViews();
        initSlider();
        iniPopularMovies();
        iniWeekMovies();

        loading_dialog.startLoading();
    }

    private void iniWeekMovies() {

        select apiInterface= apiclient.getApiClient().create(select.class);
        Call<List<MovieResponse>> call =apiInterface.getPopular();
        call.enqueue(new Callback<List<MovieResponse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {

                respons = (ArrayList<MovieResponse>) response.body();
                adapter=new Movie_adapter(getApplicationContext(), respons,HomeActivity.this);
                moviesRVweek.setAdapter(adapter);
                moviesRVweek.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
                loading_dialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error occurred. Please try again..", Toast.LENGTH_SHORT).show();
                loading_dialog.dismissDialog();
            }
        });

        /*Movie_adapter week_movie_adapter=new Movie_adapter(this, DataSource.getWeekMovies(),this);
        moviesRVweek.setAdapter(week_movie_adapter);
        moviesRVweek.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));*/
    }

    private void iniPopularMovies() {

        select apiInterface= apiclient.getApiClient().create(select.class);
        Call<List<MovieResponse>> call =apiInterface.getAfter();
        call.enqueue(new Callback<List<MovieResponse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {

                respons = (ArrayList<MovieResponse>) response.body();
                adapter=new Movie_adapter(getApplicationContext(), respons,HomeActivity.this);
                MovieRV.setAdapter(adapter);
                MovieRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
            }

            @Override
            public void onFailure(Call<List<MovieResponse>> call, Throwable t) {

                loading_dialog.dismissDialog();
            }
        });

        /*//movie list
        Movie_adapter movie_adapter=new Movie_adapter(this, DataSource.getPopularMovies(),this);
        MovieRV.setAdapter(movie_adapter);
        MovieRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));*/
    }

    private void initSlider() {

        select apiInterface= apiclient.getApiClient().create(select.class);
        Call<List<MovieResponse>> call =apiInterface.getSlider();
        call.enqueue(new Callback<List<MovieResponse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {

                listSlide = (ArrayList<MovieResponse>) response.body();
                sliderAdapter=new SliderPageAdapter(getApplicationContext(),listSlide);
                sliderPager.setAdapter(sliderAdapter);

                //setTimer
                Timer timer=new Timer();
                timer.scheduleAtFixedRate(new SliderTime(),4000,5000);

                indicator.setupWithViewPager(sliderPager,true);

                loading_dialog.dismissDialog();
            }

            @Override
            public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Error occurred. Please try again..", Toast.LENGTH_SHORT).show();
                loading_dialog.dismissDialog();
            }
        });


        /*listSlide=new ArrayList<>();
        listSlide.add(new Slide(R.drawable.avenger,"Slider Title"));
        listSlide.add(new Slide(R.drawable.ready_player_one,"Slider Title"));
        listSlide.add(new Slide(R.drawable.avenger,"Slider Title"));
        listSlide.add(new Slide(R.drawable.ready_player_one,"Slider Title"));*/

        /*SliderPageAdapter adapter=new SliderPageAdapter(this,listSlide);
        sliderPager.setAdapter(adapter);*/



    }

    private void initViews() {
        sliderPager=findViewById(R.id.slider_pager);
        indicator=findViewById(R.id.indicator);
        MovieRV=findViewById(R.id.RV_movies);
        moviesRVweek=findViewById(R.id.RV_movies_this_week);

        allMovie=findViewById(R.id.btn_recent_view_movie);
        allMovie.setOnClickListener(view -> {
            Intent intent=new Intent(HomeActivity.this,AllMovieActivity.class);
            String two="two";
            intent.putExtra("number",two);
            startActivity(intent);
        });


        pupularMovie=findViewById(R.id.btn_popular_all_movie);

        pupularMovie.setOnClickListener(view -> {
            Intent intent=new Intent(HomeActivity.this,AllMovieActivity.class);
            String one="one";
            intent.putExtra("number",one);
            startActivity(intent);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMovieClick(MovieResponse movie, ImageView movieImageView) {

        Intent intent=new Intent(this,MovieDetailsActivity.class);
        intent.putExtra("mv_id",movie.getId());
        intent.putExtra("title",movie.getName());
        intent.putExtra("image",movie.getPoter());
        intent.putExtra("imageCover",movie.getCover());
        intent.putExtra("dics",movie.getDescription());
        intent.putExtra("year",movie.getYear());
        intent.putExtra("type",movie.getType());
        intent.putExtra("language",movie.getLanguage());

        //animation
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this, movieImageView,"sharedName");

        startActivity(intent,options.toBundle());
        //startActivity(intent);

        //Toast.makeText(this,movie.getName(),Toast.LENGTH_LONG).show();

    }


    class SliderTime extends TimerTask{
        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem()<listSlide.size()-1)
                    {
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);
                    }
                    else {
                        sliderPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}