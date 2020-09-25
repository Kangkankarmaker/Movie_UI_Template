package com.example.movieuitemplate.adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.movieuitemplate.R;
import com.example.movieuitemplate.pojo.MovieResponse;
import com.example.movieuitemplate.ui.HomeActivity;
import com.example.movieuitemplate.ui.MoviePlayerActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SliderPageAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<MovieResponse> mList;

    public static final String BASE_URL = "http://untearable-trays.000webhostapp.com/movie/img/";

    public SliderPageAdapter(Context mContext, ArrayList<MovieResponse> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout=inflater.inflate(R.layout.slide_item,null);

        //View slideLayout=LayoutInflater.from(mContext).inflate(R.layout.slide_item,container,false);

        ImageView imageView=slideLayout.findViewById(R.id.slider_img);
        ImageView imageBG=slideLayout.findViewById(R.id.imageView2);
        TextView title=slideLayout.findViewById(R.id.slide_title);
        ImageView view=slideLayout.findViewById(R.id.btn_view);


        //FloatingActionButton actionButton=slideLayout.findViewById(R.id.floatingActionButton2);

        String photoString = mList.get(position).getCover();
        Uri photoUri = Uri.parse(BASE_URL+photoString);
        Picasso.get().load(photoUri).into(imageView);

        imageBG.setImageResource(R.drawable.gradint_bd);
        //actionButton.setImageResource(R.drawable.gradint_bd);

        title.setText(mList.get(position).getName());

        container.addView(slideLayout);
        return slideLayout;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
