package com.jodelapp.data;


import com.jodelapp.data.api.ApiService;
import com.jodelapp.data.managers.CurrentUserManager;

public interface DataComponent {

    ApiService exposeApiService();

    CurrentUserManager exposeCurrentUserManager();
}
