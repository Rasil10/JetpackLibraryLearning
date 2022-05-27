package com.rasil.viewmodel1.model

import kotlinx.coroutines.delay

class UserRepository {

    suspend fun getUsers(): List<User>{
        delay(1500)
        val users: List<User> = listOf(
            User(1,"User 1"),
            User(2,"User 2"),
            User(3,"User 3"),
            User(4,"User 4"),
        )

        return users
    }
}