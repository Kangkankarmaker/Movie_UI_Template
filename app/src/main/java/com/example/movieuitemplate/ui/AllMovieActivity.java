package com.example.movieuitemplate.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.movieuitemplate.R;
import com.example.movieuitemplate.fragment.PopularMovieFragment;
import com.example.movieuitemplate.fragment.ThisWeekFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class AllMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movie);

        TabLayout tabLayout=findViewById(R.id.tab_layout);
        ViewPager viewPager =findViewById(R.id.view_pager);

        viewpagerAdapter viewpagerAdapter=new viewpagerAdapter(getSupportFragmentManager());

        viewpagerAdapter.addFragment(new ThisWeekFragment(),"This week");
        viewpagerAdapter.addFragment(new PopularMovieFragment(),"Popular Movies");


        viewPager.setAdapter(viewpagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        String number=getIntent().getExtras().getString("number");

            if(number.equals("one")){
                viewPager.setCurrentItem(0);
            }else if(number.equals("two")){
                viewPager.setCurrentItem(1);
            }

        }
    }

    class viewpagerAdapter extends FragmentPagerAdapter {


        private ArrayList<Fragment>fragments;
        private ArrayList<String>titles;


        viewpagerAdapter(FragmentManager fm){
            super(fm);
            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment,String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
