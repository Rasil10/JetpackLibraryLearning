package com.rasil.viewmodel1

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingTotal : Int) : ViewModel() {

    val total = MutableLiveData<Int>()

    @Bindable
    val inputText = MutableLiveData<String>()

    fun updateCount(){
        total.value = (total.value)?.plus(inputText.value!!.toInt())
    }

}