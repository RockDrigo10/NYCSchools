package com.example.fiend.nycschools.view.schoolActivity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.fiend.nycschools.R;
import com.example.fiend.nycschools.model.NYCSchool;
import com.example.fiend.nycschools.model.NYCSchoolScore;

import java.util.ArrayList;
import java.util.List;

public class SchoolsView extends AppCompatActivity implements SchoolViewContract.View, SchoolViewAdapter.onClickListener {

    RecyclerView rvSchools;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    SchoolViewPresenter presenter;
    private List<NYCSchool> itemList = new ArrayList<>();
    private SchoolViewAdapter schoolViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new SchoolViewPresenter();
        rvSchools = (RecyclerView) findViewById(R.id.rvSchools);
        initRecyclerView();
        presenter.attachView(this);
        presenter.showSchoolList();

    }

    private void initRecyclerView() {
        schoolViewAdapter = new SchoolViewAdapter(itemList, this);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        rvSchools.setLayoutManager(layoutManager);
        rvSchools.setItemAnimator(itemAnimator);
        rvSchools.setAdapter(schoolViewAdapter);
        rvSchools.setHasFixedSize(true);
    }

    @Override
    public void showError(String e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.removeView();
    }

    @Override
    public void onListUpdated(List<NYCSchool> itemList) {
        this.itemList = itemList;
        schoolViewAdapter.setList(itemList);
        schoolViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void sendSatScores(List<NYCSchoolScore> scores) {
        final Dialog dialogCustom = new Dialog(this);
        if (scores.size() > 0) {
            dialogCustom.setContentView(R.layout.school_sat_scores);
            TextView tvMaths = (TextView) dialogCustom.findViewById(R.id.tvMaths);
            TextView tvReading = (TextView) dialogCustom.findViewById(R.id.tvReading);
            TextView tvWriting = (TextView) dialogCustom.findViewById(R.id.tvWriting);
            TextView tvSchoolName = (TextView) dialogCustom.findViewById(R.id.tvSchoolName);
            tvMaths.setText(scores.get(0).getSatMathAvgScore());
            tvReading.setText(scores.get(0).getSatCriticalReadingAvgScore());
            tvWriting.setText(scores.get(0).getSatWritingAvgScore());
            tvSchoolName.setText(scores.get(0).getSchoolName());
        } else{
            dialogCustom.setContentView(R.layout.empty_sat_scores);
        }
        dialogCustom.show();
    }

    @Override
    public void onSchoolSelected(NYCSchool school) {
        presenter.getSatScores(school.getDbn());
    }
}