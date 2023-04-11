package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.data.network.ApiClient
import com.red_velvet_cake.dailytodo.domain.TodoService

class TodoServiceImpl(private val apiClient: ApiClient) : TodoService {
    override fun login(username: String, password: String, callback: (Boolean, String) -> Unit) {
        apiClient.login(username, password, callback)
    }

}