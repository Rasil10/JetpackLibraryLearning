package com.rasil.viewmodel1

import kotlinx.coroutines.*

class StructuredUserDataManager {
    var count = 0
    lateinit var deferred: Deferred<Int>
    suspend fun getTotalUserAccount(): Int {
        coroutineScope {
            launch(Dispatchers.IO) {
                delay(1000)
                count = 15
            }

            deferred = async(Dispatchers.IO) {
                delay(3000)
                return@async 66
            }
        }
        return count + deferred.await()
    }
}