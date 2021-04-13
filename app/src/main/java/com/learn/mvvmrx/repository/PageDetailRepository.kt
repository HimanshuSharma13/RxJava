package com.learn.mvvmrx.repository

import com.learn.mvvmrx.api.RetrofitClient
import com.learn.mvvmrx.model.PageInfo
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object PageDetailRepository {


    public fun getPageInformation(mObserver: Observer<in PageInfo?>?){
        RetrofitClient.retrofitInterface?.pageDetails
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe(mObserver)
    }

}