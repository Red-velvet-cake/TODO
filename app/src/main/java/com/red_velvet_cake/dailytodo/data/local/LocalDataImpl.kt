package com.red_velvet_cake.dailytodo.data.local

class LocalDataImpl() : LocalData {
    override fun getUserToken(): String? {
        return SharedPrefs.token
    }

    override fun setUserToken(userToken: String) {
        SharedPrefs.token = userToken
    }
}