package com.example.fiend.nycschools.view.schoolActivity;

import com.example.fiend.nycschools.BasePresenter;
import com.example.fiend.nycschools.BaseView;
import com.example.fiend.nycschools.model.NYCSchool;
import com.example.fiend.nycschools.model.NYCSchoolScore;

import java.util.List;

public interface SchoolViewContract {


    interface View extends BaseView {
        void onListUpdated(List<NYCSchool> item);
        void sendSatScores(List<NYCSchoolScore> scores);
    }

    interface Presenter extends BasePresenter<View> {
        void showSchoolList();
        void getSatScores(String dbn);
    }
}
