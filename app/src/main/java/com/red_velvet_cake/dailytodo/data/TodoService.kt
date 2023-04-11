package com.red_velvet_cake.dailytodo.data

import com.red_velvet_cake.dailytodo.domain.UpdatePersonalStatusResponse
import java.io.IOException

interface TodoService {
    fun updatePersonalTodoStatus(
        onSuccess: (updatePersonalStatusResponse: UpdatePersonalStatusResponse) -> Unit,
        onFailure: (e: IOException) -> Unit
    )
}