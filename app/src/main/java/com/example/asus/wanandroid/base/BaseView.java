package com.example.asus.wanandroid.base;

import android.app.Activity;

public interface BaseView<T extends BasePresenter> {


    void setPresenter(T presenter);

    Activity getActivity();
}
