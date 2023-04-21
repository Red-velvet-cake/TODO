package com.red_velvet_cake.dailytodo.data.local

import android.content.Context
import android.content.SharedPreferences

private const val USERNAME = "username"

object SharedPrefs {
    private lateinit var sharedPreferences: SharedPreferences
    private const val SHARED_PREF = "TodoPrefs"
    private const val AUTH_TOKEN = "auth_token"

    fun initPrefUtil(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
    }

    var token: String?
        get() = sharedPreferences.getString(AUTH_TOKEN, null)
        set(value) {
            sharedPreferences.edit().putString(AUTH_TOKEN, value).apply()
        }

    var userName: String?
        get() = sharedPreferences.getString(USERNAME, null)
        set(value) {
            sharedPreferences.edit().putString(USERNAME, value).apply()
        }
}