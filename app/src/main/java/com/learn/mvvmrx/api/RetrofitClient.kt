package com.learn.mvvmrx.api

import com.learn.mvvmrx.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    val  retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }



    var retrofitInterface: RetrofitInterface? = null
        get() {
            if (field == null) {
                field = retrofit!!.create(RetrofitInterface::class.java)
            }
            return field
        }


}