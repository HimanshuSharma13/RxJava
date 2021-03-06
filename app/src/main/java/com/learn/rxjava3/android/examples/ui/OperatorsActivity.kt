package com.learn.rxjava3.android.examples.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.learn.rxjava3.android.examples.R
import com.learn.rxjava3.android.examples.ui.operators.*

class OperatorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operators)
    }

    fun startSimpleActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, SimpleExampleActivity::class.java))
    }

    fun startMapActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, MapExampleActivity::class.java))
    }

    fun startZipActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, ZipExampleActivity::class.java))
    }

    fun startLoginActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, LoginActivity::class.java))
    }

    fun startZipFlatMapFilterExampleActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, ZipFlatMapFilterExampleActivity::class.java))
    }

    fun startTimerActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, TimerExampleActivity::class.java))
    }

    fun startFilterActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, FilterExampleActivity::class.java))
    }

    fun startConcatActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, ConcatExampleActivity::class.java))
    }

    fun startMergeActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, MergeExampleActivity::class.java))
    }

    fun startDelayActivity(view: View) {
        startActivity(Intent(this@OperatorsActivity, DelayExampleActivity::class.java))
    }

}