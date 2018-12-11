package com.nkmr.mycoroutine

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.util.Log.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch


class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {

        d("IntentService", "onHandleIntent Start")
        fun funcAsync() = async(CommonPool) {
            delay(2000)
            return@async "yes"
        }

        launch(UI) {
            while (true) {
                Thread.sleep(1000)
                d(
                    "sleep",
                    "koko"
                )

                var str = funcAsync().await()
                d(
                    "await result",
                    str
                )
            }
        }

//        val timer = Timer()
//        timer.schedule(timerTask {
//            Log.d("timer", "end $incrementNumber")
//        }, 1000)

//        val launch = launch(UI) {
//            funcAsync().await()
//            Log.d("coroutine", "end $incrementNumber")
//        }

//        object : AsyncTask<Void, Void, Void>() {
//            override fun doInBackground(vararg p0: Void?): Void? {
//                launch(UI) {
//                    funcAsync().await()
//                    Log.d("coroutine", "end $incrementNumber")
//                }
//                return null
//            }
//        }.execute()

//        var t = Thread {
//            kotlin.run {
//
//            }
//        }.start()

//        var obj = Object()
//        synchronized (obj) {
//            obj.wait()
//            launch(UI) {
//                funcAsync().await()
//                Log.d("coroutine", "end $incrementNumber")
//                obj.notifyAll()
//                obj.notify()
//            }
//        }

//        val lock = java.util.concurrent.locks.ReentrantLock()
//        thread {
//            launch(UI) {
//                lock.withLock {
//                    funcAsync().await()
//                    Log.d("coroutine", "end $incrementNumber")
//                }
//            }
//        }


    }

    override fun onDestroy() {
        super.onDestroy()
        d("IntentService.onDestroy", "koko")
    }
}