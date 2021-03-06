package com.jodelapp.features.todos.presentation;


import android.util.Log;

import com.jodelapp.data.managers.CurrentUserManager;
import com.jodelapp.data.models.User;
import com.jodelapp.features.todos.usecases.GetTodoListByUser;
import com.jodelapp.utilities.rx.RxDisposableFactory;
import com.jodelapp.utilities.rx.RxDisposables;
import com.jodelapp.utilities.rx.ThreadTransformer;

import javax.inject.Inject;

public final class UserTodoListPresenter implements UserTodoListContract.Presenter {

    private final UserTodoListContract.View view;
    private final GetTodoListByUser getTodoListByUser;
    private final ThreadTransformer threadTransformer;
    private final CurrentUserManager currentUserManager;
    private final RxDisposables disposables;

    @Inject
    public UserTodoListPresenter(UserTodoListContract.View view,
                                 GetTodoListByUser getTodoListByUser,
                                 ThreadTransformer threadTransformer,
                                 RxDisposableFactory factory,
                                 CurrentUserManager currentUserManager) {
        this.view = view;
        this.getTodoListByUser = getTodoListByUser;
        this.threadTransformer = threadTransformer;
        this.disposables = factory.get();
        this.currentUserManager = currentUserManager;
    }

    @Override
    public void onAttached() {
        disposables.add(currentUserManager.getSubject().subscribe(this::getTodoList));
    }

    private void getTodoList(final User user) {
        disposables.add(getTodoListByUser.call(user.getId())
                                         .compose(threadTransformer.applySchedulers())
                                         .subscribe(
                                                 todos -> view.loadToDoList(todos),
                                                 error -> Log.e("UserToDo", error.getMessage())
                                         ));
    }

    @Override
    public void onDetached() {
        disposables.clear();
    }
}
