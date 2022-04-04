package com.rasil.viewmodel1

import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal : Int) : ViewModel() {

    private var total = 0

    init {
        total = startingTotal
    }

    fun getTotal() = total

    fun setTotal(current: Int) {
        total += current
    }

}