package com.learn.mvvmrx.api

import com.learn.mvvmrx.model.PageInfo
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitInterface {
    @get:GET("users?page=2")
    val pageDetails: Observable<PageInfo?>?
}