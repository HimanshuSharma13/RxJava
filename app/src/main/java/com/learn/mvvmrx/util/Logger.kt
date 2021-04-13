package com.learn.mvvmrx.util

import android.util.Log
import com.learn.mvvmrx.BuildConfig

object Logger {
    fun d(key: String?, msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.d(key, msg)
        }
    }

    fun e(key: String?, msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.e(key, msg)
        }
    }
}