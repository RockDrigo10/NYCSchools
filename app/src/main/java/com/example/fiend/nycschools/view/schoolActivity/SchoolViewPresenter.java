package com.example.fiend.nycschools.view.schoolActivity;

import android.util.Log;
import com.example.fiend.nycschools.model.NYCSchool;
import com.example.fiend.nycschools.model.NYCSchoolScore;
import com.example.fiend.nycschools.remote.RemoteDataSource;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolViewPresenter implements SchoolViewContract.Presenter {


    SchoolViewContract.View view;

    @Override
    public void attachView(SchoolViewContract.View view) {

        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;

    }

    @Override
    public void showSchoolList() {

        Call<List<NYCSchool>> schoolList = RemoteDataSource.getCall();
        schoolList.enqueue(new Callback<List<NYCSchool>>() {
            @Override
            public void onResponse(Call<List<NYCSchool>> call, Response<List<NYCSchool>> response) {
                view.onListUpdated(response.body());
            }

            @Override
            public void onFailure(Call<List<NYCSchool>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void getSatScores(String dbn) {

        Call<List<NYCSchoolScore>> schoolScores = RemoteDataSource.getScores(dbn);
        schoolScores.enqueue(new Callback<List<NYCSchoolScore>>() {
            @Override
            public void onResponse(Call<List<NYCSchoolScore>> call, Response<List<NYCSchoolScore>> response) {
                view.sendSatScores(response.body());
            }

            @Override
            public void onFailure(Call<List<NYCSchoolScore>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

}
