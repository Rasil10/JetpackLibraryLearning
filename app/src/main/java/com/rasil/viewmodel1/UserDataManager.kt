package com.rasil.viewmodel1

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserDataManager {

    // Unstructured concurency
    suspend fun getTotalUserAccount():Int{
        var count = 0
        CoroutineScope(IO).launch {
            delay(1000)
            count= 15
        }

        val deferred = CoroutineScope(IO).async {
            delay(3000)
            return@async 66
        }


        return count + deferred.await()
    }

}