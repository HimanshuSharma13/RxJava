package com.learn.mvvmrx.viewModel

import android.content.Context
import com.learn.mvvmrx.api.AndroidUserAPI
import com.learn.mvvmrx.model.AndroidVersion
import com.learn.mvvmrx.model.Datum
import com.learn.mvvmrx.model.PageInfo
import com.learn.mvvmrx.util.RetrofitAPI
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class PageDetailViewModel(private val mContext: Context) : Observable() {
    private val mVersion: AndroidVersion? = null
    var dataList: List<Datum?>?
        private set

    fun getPageInformation(mObserver: Observer<in PageInfo?>?) {
        RetrofitAPI.retrofit.create(AndroidUserAPI::class.java)
                .androidVersion
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe(mObserver)
    }

    fun updateVersionDataList(list: List<Datum?>?) {
        dataList = list
    }

    fun hasData(): Boolean {
        return if (dataList != null && dataList!!.size > 0) true else false
    }

    init {
        dataList = ArrayList()
    }
}