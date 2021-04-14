package com.learn.mvvmrx.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.learn.mvvmrx.R
import com.learn.mvvmrx.databinding.ActivityMainBinding
import com.learn.mvvmrx.model.Datum
import com.learn.mvvmrx.model.PageInfo
import com.learn.mvvmrx.util.Logger
import com.learn.mvvmrx.viewModel.PageDetailViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private var mMainActivityBinding: ActivityMainBinding? = null
    private var mPageDetailViewModel: PageDetailViewModel? = null
    private var mGetPageInfo: DisposableObserver<*>? = null
    private var mPageDetailList: RecyclerView? = null
    private var mVersionAdapter: PageInfoAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        setUpView()
    }

    private fun initDataBinding() {
        mMainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mPageDetailViewModel = PageDetailViewModel(this)
        mMainActivityBinding?.pageDetailViewModel= mPageDetailViewModel
    }

    private fun setUpView() {
        setSupportActionBar(mMainActivityBinding!!.toolbar)
        mPageDetailList = mMainActivityBinding!!.rlVersionlist
        mPageDetailList!!.layoutManager = LinearLayoutManager(this)
        mVersionAdapter = PageInfoAdapter()
        mVersionAdapter!!.showList(mPageDetailViewModel?.dataList)
        mPageDetailList!!.adapter = mVersionAdapter
    }


    private fun getObservable(pageCount:String):Observable<PageInfo?>?{
        return mPageDetailViewModel?.getPageInformation(pageCount)
    }

    private fun getObserver(): Observer<List<Datum>?> {
        return pageDetails
    }

    private val pageDetails: DisposableObserver<List<Datum>?>
        private get() {
            mGetPageInfo = object : DisposableObserver<List<Datum>?>() {
                override fun onError(e: Throwable) {
                    Logger.d("Android error", e.message)
                }

                override fun onComplete() {
                    Logger.d("Android complete", "done")
                }

                override fun onNext(data: List<Datum>?) {
                    if (data != null && data!!.size > 0) {
                        mPageDetailViewModel!!.updateVersionDataList(data)
                        mMainActivityBinding!!.pageDetailViewModel = mPageDetailViewModel
                        updateList()
                    }
                }
            }

//           var mObservable:Observable<PageInfo?>? = mPageDetailViewModel!!.getPageInformation(mGetPageInfo as DisposableObserver<PageInfo?>)
//        mObservable?.subscribe(mGetPageInfo as DisposableObserver<PageInfo?>)
        return mGetPageInfo as DisposableObserver<List<Datum>?>
        }

    private fun updateList() {
        mVersionAdapter!!.showList(mPageDetailViewModel?.dataList)
    }

    override fun onResume() {
        super.onResume()
        pageDetails
        Observable.zip(getObservable("1")?.subscribeOn(Schedulers.io()),getObservable("2")?.subscribeOn(Schedulers.io()), BiFunction<PageInfo?,PageInfo?,List<Datum>>{
            detail1,detail2->
            var arr:ArrayList<Datum> = ArrayList<Datum>()
            detail1?.data?.let { arr.addAll(it) }
            detail2?.data?.let { arr.addAll(it) }
            return@BiFunction arr.toList()
        }).subscribeOn(Schedulers.io()).subscribe(getObserver())
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!mGetPageInfo!!.isDisposed) mGetPageInfo!!.dispose()
    }
}