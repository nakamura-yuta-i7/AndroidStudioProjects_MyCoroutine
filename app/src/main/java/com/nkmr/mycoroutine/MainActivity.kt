package com.nkmr.mycoroutine

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Log.e
import android.view.View
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch


class MainActivity : AppCompatActivity() {

    fun onButtonClick(v: View) = launch(UI) {
        UploadTaskDaemon.watch()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            e(javaClass.name, "### uncaughtException ###", throwable)
        }
    }
}

