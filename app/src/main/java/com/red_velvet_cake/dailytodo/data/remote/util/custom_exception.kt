package com.red_velvet_cake.dailytodo.data.remote.util

abstract class CustomException(message: String) : Exception(message) {
    class UnauthorizedUserException(message: String) : CustomException(message)
}