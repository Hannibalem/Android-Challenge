package com.jodelapp.views.activities;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jodelapp.DaggerDependencies;
import com.jodelapp.R;
import com.jodelapp.features.photos.presentation.UserPhotoListFragment;
import com.jodelapp.features.todos.presentation.UserTodoListView;
import com.jodelapp.features.users.presentation.UserUserListFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @Inject
    MainActivityContract.Presenter presenter;

    @BindView(R.id.tb_app)
    Toolbar tbApp;

    @BindView(R.id.navigator)
    BottomNavigationView navigator;

    @Override
    public void loadPage(final int id) {
        final Fragment fragment;
        switch (id) {
            case R.id.action_photos:
                fragment = UserPhotoListFragment.getInstance();
                break;
            case R.id.action_tasks:
                fragment = UserTodoListView.getInstance();
                break;
            default:
                fragment = UserUserListFragment.Companion.getInstance();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                                   .replace(R.id.v_container, fragment)
                                   .commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerDependencies.Companion.inject(this);
        initViews();
        presenter.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void initViews() {
        ButterKnife.bind(this);
        setSupportActionBar(tbApp);
        setNavigator();
    }

    private void setNavigator() {
        navigator.setOnNavigationItemSelectedListener(item -> {
            presenter.onLoadPage(item.getItemId());
            return true;
        });
    }
}
