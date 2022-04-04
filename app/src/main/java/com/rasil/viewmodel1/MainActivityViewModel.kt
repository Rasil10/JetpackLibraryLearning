package com.rasil.viewmodel1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal: Int) : ViewModel() {

    var total = MutableLiveData<Int>()

    init {
        total.value = startingTotal
    }

    fun setTotal(current: Int) {
        total.value = (total.value)?.plus(current)
    }

}