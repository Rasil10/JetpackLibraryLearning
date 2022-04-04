package com.rasil.viewmodel1

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private var total = 0

    fun getTotal() = total

    fun setTotal(current: Int) {
        total += current
    }

}