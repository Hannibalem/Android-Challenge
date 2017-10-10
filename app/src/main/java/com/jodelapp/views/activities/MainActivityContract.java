package com.jodelapp.views.activities;

import android.os.Bundle;
public interface MainActivityContract {

    interface View {

        void loadPage(int id);
    }

    interface Presenter {

        void onCreate(Bundle savedInstanceState);

        void onLoadPage(int id);

        void onDestroy();
    }
}
