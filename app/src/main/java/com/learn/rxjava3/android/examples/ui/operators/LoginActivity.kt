package com.learn.rxjava3.android.examples.ui.operators

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.learn.rxjava3.android.examples.R
import com.learn.rxjava3.android.examples.utils.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Function3
import io.reactivex.rxjava3.schedulers.Schedulers


class LoginActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LoginActivity"
    }

    private lateinit var btn: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        btn = findViewById(R.id.btn)
        textView = findViewById(R.id.textView)

        btn.setOnClickListener {
            doSomeWork()
        }

    }

    private fun zipList(list: List<String>): List<String> {
        return list
    }


    private fun doSomeWork() {
        var row11 = listOf<String>("1", "2", "3")
        var row22 = listOf<String>("4","5", "6")
        var row33 = listOf<String>("7","8","9")
        val observable1 = Observable.just(row11)
        val observable2 = Observable.just(row22)
        val observable3 = Observable.just(row33)
//            .observeOn(AndroidSchedulers.mainThread())
//    val observable2 = Observable.fromArray("5", "6", "7", "8")
//    val observable3 = Observable.fromArray("9", "10", "11", "12")
//    val result: Observable<List<String>> =
        Observable.zip(observable1.subscribeOn(Schedulers.io()),
            observable2.subscribeOn(Schedulers.io()),
            observable3.subscribeOn(Schedulers.io()),
            Function3<List<String>?, List<String>?, List<String>?, List<String>?> { row1, row2, row3 ->
                return@Function3 Utils.printSequentially(row1, row2, row3)
            })
            // Run on a background thread
//            .subscribeOn(Schedulers.io())
            // Be notified on the main thread
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getObserver())


    }

    private fun getObserver(): Observer<List<String>?>? {
        return object : Observer<List<String>?> {
            override fun onComplete() {
                Log.d(LoginActivity.TAG, " onComplete() : ")
            }

            override fun onSubscribe(d: Disposable?) {
                Log.d(LoginActivity.TAG, " onSubscribe : " + d?.isDisposed)
            }

            override fun onNext(t: List<String>?) {
                for (user in t!!) {
                    print(user)
                }
                Log.d(LoginActivity.TAG, " onNext : " + t.size)
            }

            override fun onError(e: Throwable?) {
                Log.d(LoginActivity.TAG, " onError() : ")
            }

        }
    }

//    private fun getObserver(): Observer<List<String>?> {
//        return object : Observer<List<String>?> {
//
//            override fun onSubscribe(d: Disposable) {
//                Log.d(LoginActivity.TAG, " onSubscribe : " + d.isDisposed)
//            }
//
//            override fun onNext(userList: List<String>?) {
//
//                for (user in userList) {
//
//                }
//                Log.d(LoginActivity.TAG, " onNext : " + userList.size)
//            }
//
//            override fun onError(e: Throwable) {
//
//                Log.d(LoginActivity.TAG, " onError : " + e.message)
//            }
//
//            override fun onComplete() {
//
//                Log.d(LoginActivity.TAG, " onComplete")
//            }
//        }
//    }
}