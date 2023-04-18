package com.red_velvet_cake.dailytodo.utils

sealed class RequestStatus {
    object Loading : RequestStatus()
    class Success(status:Boolean): RequestStatus()
    class Error(val message: String) : RequestStatus()
}