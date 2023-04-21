package com.red_velvet_cake.dailytodo.data.local

interface LocalData {
    fun getUserToken(): String?
    fun setUserToken(userToken: String)
    fun clearUserToken()
    fun saveUserName(userName: String)
    fun getUserName(): String
}