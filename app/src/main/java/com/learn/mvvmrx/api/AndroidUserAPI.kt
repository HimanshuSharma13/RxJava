package com.learn.mvvmrx.api

import com.learn.mvvmrx.model.PageInfo
import io.reactivex.Observable
import retrofit2.http.GET

interface AndroidUserAPI {
    @get:GET("users?page=2")
    val androidVersion: Observable<PageInfo?>?
}