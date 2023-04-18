package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import com.red_velvet_cake.dailytodo.utils.ResponseStatus

interface PersonalTodoStatus {
    fun handleResponseStatus(status: ResponseStatus)
}