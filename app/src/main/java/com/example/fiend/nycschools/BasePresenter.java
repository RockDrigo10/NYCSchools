package com.example.fiend.nycschools;

/**
 * Created by Rodrigo on 3/20/18.
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);
    void removeView();

}
