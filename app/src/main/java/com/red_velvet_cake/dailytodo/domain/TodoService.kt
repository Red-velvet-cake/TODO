package com.red_velvet_cake.dailytodo.domain

interface TodoService {
    fun login(username: String, password: String, callback: (Boolean, String) -> Unit)
}