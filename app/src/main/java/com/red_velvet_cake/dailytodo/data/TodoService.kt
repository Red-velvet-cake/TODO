package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.domain.GetAllPersonalTodosResponse
import java.io.IOException

interface TodoService {
    fun getAllPersonalTodos(
        onSuccess: (updatePersonalStatusResponse: GetAllPersonalTodosResponse) -> Unit,
        onFailure: (e: IOException) -> Unit
    )
}