package com.jodelapp.views.activities;

import android.os.Bundle;

import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;

import javax.inject.Inject;

public final class MainActivityPresenter implements MainActivityContract.Presenter {

    private final MainActivityContract.View view;
    private final RxDisposables disposables;
    private final int NONE_ID = 0;
    @Inject
    public MainActivityPresenter(MainActivityContract.View view,
                                 RxDisposableFactory rxDisposableFactory) {
        this.view = view;
        this.disposables = rxDisposableFactory.get();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            view.loadPage(NONE_ID);
        }
    }

    @Override
    public void onLoadPage(final int id) {
        view.loadPage(id);
    }

    @Override
    public void onDestroy() {
        disposables.clear();
    }
}
