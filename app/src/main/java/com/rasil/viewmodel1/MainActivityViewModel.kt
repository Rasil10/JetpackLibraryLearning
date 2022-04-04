package com.rasil.viewmodel1

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var count = 0

    fun getCurrentCount() = count
    fun getUpdatedCount() = ++count

}