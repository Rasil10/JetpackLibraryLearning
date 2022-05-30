package com.rasil.viewmodel1

import com.rasil.viewmodel1.db.Subscriber
import com.rasil.viewmodel1.db.SubsriberDAO

class SubsriberRepository(private val dao: SubsriberDAO) {

    val subsribers = dao.getAllSubscriber()

    suspend fun insertSubsriber(subscriber: Subscriber): Long {
        return dao.insertSubsriber(subscriber)
    }

    suspend fun updateSubsriber(subscriber: Subscriber): Int {
        return dao.updateSubscriber(subscriber)
    }

    suspend fun deleteSusbriber(subscriber: Subscriber): Int {
        return dao.deleteSubsriber(subscriber)
    }

    suspend fun deleteAllSubsriber():Int {
        return  dao.deleteAllSubsriber()
    }
}