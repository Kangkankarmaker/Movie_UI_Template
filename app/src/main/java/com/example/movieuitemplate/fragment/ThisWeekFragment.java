package com.example.movieuitemplate.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.movieuitemplate.ApiClient.apiclient;
import com.example.movieuitemplate.R;
import com.example.movieuitemplate.adapters.MovieItemClickListener;
import com.example.movieuitemplate.adapters.Movie_adapter;
import com.example.movieuitemplate.pojo.MovieResponse;
import com.example.movieuitemplate.select;
import com.example.movieuitemplate.ui.MovieDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThisWeekFragment extends Fragment implements MovieItemClickListener {

    RecyclerView rec_View;

    ArrayList<MovieResponse> respons =new ArrayList<>();
    Movie_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_this_week, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rec_View= view.findViewById(R.id.rv_thisWeek_movie);

        select apiInterface= apiclient.getApiClient().create(select.class);
        Call<List<MovieResponse>> call =apiInterface.getAfter();
        call.enqueue(new Callback<List<MovieResponse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {

                respons = (ArrayList<MovieResponse>) response.body();
                adapter=new Movie_adapter(getContext(), respons,ThisWeekFragment.this);
                rec_View.setAdapter(adapter);
                rec_View.setLayoutManager(new GridLayoutManager(getActivity(),2));

            }

            @Override
            public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Error occurred. Please try again..", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMovieClick(MovieResponse movie, ImageView movieImageView) {

        Intent intent=new Intent(getContext(), MovieDetailsActivity.class);
        intent.putExtra("mv_id",movie.getId());
        intent.putExtra("title",movie.getName());
        intent.putExtra("image",movie.getPoter());
        intent.putExtra("imageCover",movie.getCover());
        intent.putExtra("dics",movie.getDescription());
        intent.putExtra("year",movie.getYear());
        intent.putExtra("type",movie.getType());
        intent.putExtra("language",movie.getLanguage());

        //animation
        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(), movieImageView,"sharedName");

        startActivity(intent,options.toBundle());
        //startActivity(intent);

    }
}