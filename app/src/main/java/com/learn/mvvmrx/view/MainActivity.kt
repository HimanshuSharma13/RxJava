package com.learn.mvvmrx.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.learn.mvvmrx.R
import com.learn.mvvmrx.databinding.ActivityMainBinding
import com.learn.mvvmrx.model.PageInfo
import com.learn.mvvmrx.util.Logger
import com.learn.mvvmrx.viewModel.PageDetailViewModel
import io.reactivex.observers.DisposableObserver

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

    private val pageDetails: Unit
        private get() {
            mGetPageInfo = object : DisposableObserver<PageInfo?>() {
                override fun onError(e: Throwable) {
                    Logger.d("Android error", e.message)
                }

                override fun onComplete() {
                    Logger.d("Android complete", "done")
                }

                override fun onNext(data: PageInfo?) {
                    if (data != null && data.data!!.size > 0) {
                        mPageDetailViewModel!!.updateVersionDataList(data.data)
                        mMainActivityBinding!!.pageDetailViewModel = mPageDetailViewModel
                        updateList()
                    }
                }
            }
            mPageDetailViewModel!!.getPageInformation(mGetPageInfo as DisposableObserver<PageInfo?>)
        }

    private fun updateList() {
        mVersionAdapter!!.showList(mPageDetailViewModel?.dataList)
    }

    override fun onResume() {
        super.onResume()
        pageDetails
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!mGetPageInfo!!.isDisposed) mGetPageInfo!!.dispose()
    }
}