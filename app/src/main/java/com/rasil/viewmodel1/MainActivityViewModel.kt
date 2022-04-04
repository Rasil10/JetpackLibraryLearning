package com.rasil.viewmodel1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val username  = MutableLiveData<String>()

    init {
        username.value = "Test Name"
    }

}