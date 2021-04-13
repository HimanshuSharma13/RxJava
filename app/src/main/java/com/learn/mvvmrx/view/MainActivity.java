package com.learn.mvvmrx.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.learn.mvvmrx.R;
import com.learn.mvvmrx.databinding.ActivityMainBinding;
import com.learn.mvvmrx.model.PageInfo;
import com.learn.mvvmrx.util.Logger;
import com.learn.mvvmrx.viewModel.AndroidVersionViewModel;

import io.reactivex.observers.DisposableObserver;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding mMainActivityBinding;
    private AndroidVersionViewModel mAndroidVersionViewModel;
    private DisposableObserver mGetAndroidVersion;
    private RecyclerView mVersionList;
    private VersionAdapter mVersionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setUpView();
    }

    private void initDataBinding() {
        mMainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mAndroidVersionViewModel = new AndroidVersionViewModel(this);
        mMainActivityBinding.setAndroidVersionViewModel(mAndroidVersionViewModel);
    }

    private void setUpView() {
        setSupportActionBar(mMainActivityBinding.toolbar);

        mVersionList = mMainActivityBinding.rlVersionlist;
        mVersionList.setLayoutManager(new LinearLayoutManager(this));
        mVersionAdapter = new VersionAdapter();
        mVersionAdapter.showList(mAndroidVersionViewModel.getDataList());
        mVersionList.setAdapter(mVersionAdapter);
    }

    private void getAndroidVersion() {
        mGetAndroidVersion = new DisposableObserver<PageInfo>() {
            @Override
            public void onNext(PageInfo data) {
                if (data != null && data.data.size() > 0) {
                    mAndroidVersionViewModel.updateVersionDataList(data.data);
                    mMainActivityBinding.setAndroidVersionViewModel(mAndroidVersionViewModel);
                    updateList();
                }
            }

            @Override
            public void onError(Throwable e) {
                Logger.d("Android error", e.getMessage());
            }
            @Override
            public void onComplete() {
                Logger.d("Android complete","done");
            }
        };

        mAndroidVersionViewModel.getAndroidVersionList(mGetAndroidVersion);
    }

    private void updateList() {
        mVersionAdapter.showList(mAndroidVersionViewModel.getDataList());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAndroidVersion();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!mGetAndroidVersion.isDisposed())
        mGetAndroidVersion.dispose();
    }
}
