package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import java.io.IOException

interface TodoService {
    fun updatePersonalTodoStatus(
        userId: String,
        updatedPersonalTodoStatus: Int,
        onSuccess: (updatePersonalStatusResponse: UpdatePersonalStatusResponse) -> Unit,
        onFailure: (e: IOException) -> Unit
    )
}