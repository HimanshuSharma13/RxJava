package com.learn.mvvmrx.repository

import com.learn.mvvmrx.api.RetrofitClient
import com.learn.mvvmrx.model.PageInfo
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object PageDetailRepository {


    public fun getPageInformation(pageCount:String): Observable<PageInfo?>? {
       return RetrofitClient.retrofitInterface?.getPageDetails(pageCount)
                ?.observeOn(AndroidSchedulers.mainThread())
//                ?.subscribeOn(Schedulers.io())
//                ?.subscribe(mObserver)
    }

}