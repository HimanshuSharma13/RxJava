package com.learn.mvvmrx.api;

import com.learn.mvvmrx.model.AndroidVersionResposne;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AndroidUserAPI {

    @GET("users?page=2")
    Observable<AndroidVersionResposne> getAndroidVersion();
}
