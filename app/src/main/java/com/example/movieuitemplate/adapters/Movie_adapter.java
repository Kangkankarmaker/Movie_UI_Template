package com.example.movieuitemplate.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieuitemplate.R;
import com.example.movieuitemplate.pojo.MovieResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Movie_adapter extends RecyclerView.Adapter<Movie_adapter.MyViewHolder> {


    Context context;
    ArrayList<MovieResponse> mData;
    MovieItemClickListener movieItemClickListener;
    public static final String BASE_URL = "http://untearable-trays.000webhostapp.com/movie/img/";



    public Movie_adapter(Context context, ArrayList<MovieResponse> mData, MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.mData = mData;
        this.movieItemClickListener = movieItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewgroup, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_movie,viewgroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.textView.setText(mData.get(position).getName());

        String photoString = mData.get(position).getPoter();
        Uri photoUri = Uri.parse(BASE_URL+photoString);
        Picasso.get().load(photoUri).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView  imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            textView=itemView.findViewById(R.id.item_movie_title);
            imageView=itemView.findViewById(R.id.item_movie_img);

            itemView.setOnClickListener(view ->
                    movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()),imageView));

        }
    }
}
