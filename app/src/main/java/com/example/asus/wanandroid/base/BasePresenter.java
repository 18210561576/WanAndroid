package com.example.asus.wanandroid.base;

public interface BasePresenter <T extends BaseView>{


    void attachView(T baseView);

    void detachView();

}
