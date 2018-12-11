package com.nkmr.mycoroutine

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

object AsyncModel {
    private var i = 0
    fun returnIntAsync() = async(CommonPool) {
        i++
        delay(2000)
        return@async i
    }
}