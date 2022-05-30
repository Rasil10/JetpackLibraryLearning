package com.rasil.viewmodel1.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubsriberDAO {

    @Insert
    suspend fun insertSubsriber(subscriber: Subscriber): Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber): Int

    @Delete
    suspend fun deleteSubsriber(subscriber: Subscriber) : Int

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAllSubsriber() : Int

    @Query("SELECT * FROM SUBSCRIBER_DATA_TABLE")
    fun getAllSubscriber(): LiveData<List<Subscriber>>
}