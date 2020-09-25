package com.example.movieuitemplate.ApiClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiclient{

        private static final String BASE_URL="https://untearable-trays.000webhostapp.com/movie/";
        private static Retrofit retrofit;

        public static Retrofit getApiClient()
        {
            if (retrofit==null){
                retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            }
            return retrofit;
        }
}
