package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import java.io.IOException

class PersonalTodoDetailsPresenter(
    private val view: PersonalTodoDetailsView,
) {

    private val todoService = TodoServiceImpl()

    fun updatePersonalTodoStatus(
        todoId: String,
        newTodoStatus: Int,
    ) {
        todoService.updatePersonalTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdatePersonalTodoStatusFailure
        )
    }

    private fun onUpdatePersonalTodoStatusFailure(exception: IOException) {
        view.showTodoStatusUpdatedFailure(exception)
    }
}