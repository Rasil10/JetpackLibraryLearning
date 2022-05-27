package com.rasil.viewmodel1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.rasil.viewmodel1.model.User
import com.rasil.viewmodel1.model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {

    private var userRepository = UserRepository()
//    val users = MutableLiveData<List<User>>()
//
//
//    fun getUserData() {
//        viewModelScope.launch {
//            var result: List<User>? = null
//            withContext(Dispatchers.IO) {
//                result = userRepository.getUsers()
//            }
//            users.value = result
//        }
//    }


    val users = liveData(Dispatchers.IO) {
        val results = userRepository.getUsers()
        emit(results)
    }

}