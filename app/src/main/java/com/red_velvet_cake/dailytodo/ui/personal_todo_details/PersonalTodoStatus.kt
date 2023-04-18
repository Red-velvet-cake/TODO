package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import com.red_velvet_cake.dailytodo.utils.RequestStatus

interface PersonalTodoStatus {
    fun handleRequestStatus(status: RequestStatus)
}