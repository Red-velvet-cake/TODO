package com.red_velvet_cake.dailytodo.utils

sealed class ResponseStatus {
    object Loading : ResponseStatus()
    class Success(val isSuccess:Boolean): ResponseStatus()
    class Error(val message: String) : ResponseStatus()
}