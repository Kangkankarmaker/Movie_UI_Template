package com.example.movieuitemplate.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movieuitemplate.ApiClient.apiclient;
import com.example.movieuitemplate.R;
import com.example.movieuitemplate.adapters.CastAdapter;
import com.example.movieuitemplate.adapters.Movie_adapter;
import com.example.movieuitemplate.models.Cast;
import com.example.movieuitemplate.pojo.MovieRating;
import com.example.movieuitemplate.pojo.MovieResponse;
import com.example.movieuitemplate.select;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView movieImg,movieCoverimg;
    TextView title,titleDec,year,language,type,ret_person;
    private FloatingActionButton actionButton;
    private RecyclerView recyclerView;
    private CastAdapter castAdapter;

    private RatingBar rBar;


    public static final String BASE_URL = "http://untearable-trays.000webhostapp.com/movie/img/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        initView();
        setUpRVCast();
        setUpIntent();
    }

    private void initView() {
        recyclerView=findViewById(R.id.RV_cast);

        movieImg=findViewById(R.id.details_movie_img);
        movieCoverimg=findViewById(R.id.details_movie_cover);
        ret_person=findViewById(R.id.ret_person);
        title=findViewById(R.id.details_movie_title);
        titleDec=findViewById(R.id.details_movie_dec);
        year=findViewById(R.id.details_movie_year);
        type=findViewById(R.id.details_movie_type);
        language=findViewById(R.id.details_movie_lang);
        actionButton=findViewById(R.id.details_movie_fab);
        rBar=findViewById(R.id.ratingBar);


        actionButton.setOnClickListener(view -> {
            Intent intent=new Intent(MovieDetailsActivity.this,MoviePlayerActivity.class);
            startActivity(intent);
        });
    }

    private void setUpIntent() {


        String movieid=getIntent().getExtras().getString("mv_id");

        String movieTitle=getIntent().getExtras().getString("title");
        String imageRec=getIntent().getExtras().getString("image");
        String imageRecCover=getIntent().getExtras().getString("imageCover");
        String movieDec=getIntent().getExtras().getString("dics");
        String movieYear=getIntent().getExtras().getString("year");
        String movieType=getIntent().getExtras().getString("type");
        String movieLanguage=getIntent().getExtras().getString("language");

        Uri poster = Uri.parse(BASE_URL+imageRec);

        Uri coverImg = Uri.parse(BASE_URL+imageRecCover);
        Picasso.get().load(poster).into(movieImg);
        Picasso.get().load(coverImg).into(movieCoverimg);

        title.setText(movieTitle);
        titleDec.setText(movieDec);
        year.setText(movieYear);
        language.setText(movieLanguage);
        type.setText(movieType);

        movieCoverimg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim));
        actionButton.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim));

        getRating(movieid);

    }

    private void setUpRVCast() {
        List<Cast>mdata=new ArrayList<>();
        mdata.add(new Cast("Kangkan",R.drawable.avenger));
        mdata.add(new Cast("dip",R.drawable.avenger));
        mdata.add(new Cast("n",R.drawable.avenger));
        mdata.add(new Cast("a",R.drawable.avenger));
        mdata.add(new Cast("k",R.drawable.avenger));
        mdata.add(new Cast("g",R.drawable.avenger));
        mdata.add(new Cast("n",R.drawable.avenger));
        mdata.add(new Cast("a",R.drawable.avenger));
        mdata.add(new Cast("k",R.drawable.avenger));

        castAdapter=new CastAdapter(this,mdata);
        recyclerView.setAdapter(castAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));


    }



    private void getRating(String movieid) {
        select apiInterface= apiclient.getApiClient().create(select.class);
        Call<List<MovieRating>> call =apiInterface.getRate(movieid);
        call.enqueue(new Callback<List<MovieRating>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<MovieRating>> call, Response<List<MovieRating>> response) {

                List<MovieRating>ratings=  response.body();

                for (MovieRating r  : ratings)
                {
                    float numOfStars;
                    numOfStars=r.getAvgRating();
                    rBar.setRating(numOfStars);


                    String pNumber;
                    pNumber=r.getTotalRating();
                    ret_person.setText(pNumber);

                }
            }

            @Override
            public void onFailure(Call<List<MovieRating>> call, Throwable t) {
                Toast.makeText(MovieDetailsActivity.this, "Error occurred. Please try again..", Toast.LENGTH_SHORT).show();

            }
        });
    }

}