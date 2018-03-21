package com.example.fiend.nycschools;

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);
    void removeView();

}
