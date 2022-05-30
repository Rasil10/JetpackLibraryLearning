package com.rasil.viewmodel1

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasil.viewmodel1.db.Subscriber
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubsriberRepository) : ViewModel() {
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearOrDeleteButtonText = MutableLiveData<String>()

    val subscribers = repository.subsribers

    private var isUpdateOrDelete = false
    private lateinit var subsriberToUpdateOrDelete: Subscriber


    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if (inputName.value.isNullOrEmpty())
            statusMessage.value = Event("Enter Subscriber Name")
        else if (inputEmail.value.isNullOrEmpty())
            statusMessage.value = Event("Enter Subscriber Email")
        else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value).matches())
            statusMessage.value = Event("Enter Valid Email")
        else {
            if (isUpdateOrDelete) {
                subsriberToUpdateOrDelete.name = inputName.value!!
                subsriberToUpdateOrDelete.email = inputEmail.value!!
                updateSubsriber(subsriberToUpdateOrDelete)
            } else {
                val name = inputName.value!!
                val email = inputEmail.value!!
                insertSubscriber(Subscriber(0, name, email))
                inputName.value = ""
                inputEmail.value = ""
            }
        }

    }

    fun clearOrDelete() {
        if (isUpdateOrDelete) {
            deleteSubsriber(subsriberToUpdateOrDelete)
        } else {
            deleteAllSubsriber()
        }
    }

    fun insertSubscriber(subscriber: Subscriber) = viewModelScope.launch {
        val newRowId = repository.insertSubsriber(subscriber)
        if (newRowId > -1)
            statusMessage.value = Event("Subscriber Inserted Successfully")
        else
            statusMessage.value = Event("Subscriber Cannot be Inserted")
    }

    fun updateSubsriber(subscriber: Subscriber) = viewModelScope.launch {
        val noOfROws = repository.updateSubsriber(subscriber)

        if (noOfROws > 0) {
            inputName.value = ""
            inputEmail.value = ""

            saveOrUpdateButtonText.value = "Save"
            clearOrDeleteButtonText.value = "Clear All"

            isUpdateOrDelete = false
            statusMessage.value = Event("$noOfROws Subscriber Updated Successfully")
        } else
            statusMessage.value = Event("Subscriber Cannot Be Updated Now.")


    }

    fun deleteSubsriber(subscriber: Subscriber) = viewModelScope.launch {

        val noOfDeleted = repository.deleteSusbriber(subscriber)

        if (noOfDeleted > 0) {
            inputName.value = ""
            inputEmail.value = ""

            saveOrUpdateButtonText.value = "Save"
            clearOrDeleteButtonText.value = "Clear All"

            isUpdateOrDelete = false
            statusMessage.value = Event("$noOfDeleted Subscriber Deleted Successfully")
        } else
            statusMessage.value = Event("Subscriber cannot be Deleted now")


    }

    fun deleteAllSubsriber() = viewModelScope.launch {
        val noOfDeleted = repository.deleteAllSubsriber()
        if (noOfDeleted > 0)
            statusMessage.value = Event("$noOfDeleted Subscriber Deleted Successfully")
        else
            statusMessage.value = Event("Subscriber cannot be Deleted Successfully")

    }

    fun initUpdateOrDelete(subscriber: Subscriber) {
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email

        saveOrUpdateButtonText.value = "Update"
        clearOrDeleteButtonText.value = "Delete"

        isUpdateOrDelete = true
        subsriberToUpdateOrDelete = subscriber
    }
}