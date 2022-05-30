package com.rasil.viewmodel1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SubsriberViewModelFactory(private val repository: SubsriberRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
            return SubscriberViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}