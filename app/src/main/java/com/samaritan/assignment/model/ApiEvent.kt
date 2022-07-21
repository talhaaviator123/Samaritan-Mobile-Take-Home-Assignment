package com.samaritan.assignment.model

sealed class ApiEvent{
        object Success:ApiEvent()
        class Failure(val errorText:String):ApiEvent()
        object Loading:ApiEvent()
        object Empty:ApiEvent()
    }