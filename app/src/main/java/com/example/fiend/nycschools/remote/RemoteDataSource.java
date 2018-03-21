package com.example.fiend.nycschools.remote;

import com.example.fiend.nycschools.model.NYCSchool;
import com.example.fiend.nycschools.model.NYCSchoolScore;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 8/17/17.
 */

public class RemoteDataSource {

    //https://data.cityofnewyork.us/resource/734v-jeq5.json?dbn=01M292
    public static final String BASE_URL = "https://data.cityofnewyork.us/";
    public static final String PATH = "resource/97mf-9njv.json";

    public static final String BASE_URL_SCORES = "https://data.cityofnewyork.us/";
    public static final String PATH_SCORES = "resource/734v-jeq5.json";


    public static Retrofit create(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }

    public static Retrofit scores(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();


        Retrofit retrofitScores = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL_SCORES)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofitScores;

    }


    /**
     * Network call for list of schools
    */
    public static Call<List<NYCSchool>> getCall() {
        Retrofit retrofit = create();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        return retrofitService.getRetrofitServiceData();
    }

    public interface RetrofitService {

        @GET(PATH)
        Call<List<NYCSchool>> getRetrofitServiceData();
    }

    /**
     * Network call for SAT scores
     */

    public static Call<List<NYCSchoolScore>> getScores(String dbn) {
        Retrofit retrofit = scores();
        RetrofitScoreService retrofitService = retrofit.create(RetrofitScoreService.class);
        return retrofitService.getRetrofitServiceDataScore(dbn);
    }

    public interface RetrofitScoreService {

        @GET(PATH_SCORES)
        Call<List<NYCSchoolScore>> getRetrofitServiceDataScore(@Query("dbn") String tag);
    }

}
