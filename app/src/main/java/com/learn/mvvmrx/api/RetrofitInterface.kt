package com.learn.mvvmrx.api

import com.learn.mvvmrx.model.PageInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @get:GET("users?page=2")
    val pageDetails: Observable<PageInfo?>?

    @GET("users")
    fun getPageDetails(@Query("page") pageCount:String):Observable<PageInfo?>?
}