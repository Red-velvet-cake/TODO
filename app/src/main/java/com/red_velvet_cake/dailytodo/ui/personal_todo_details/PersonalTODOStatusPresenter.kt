package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import com.red_velvet_cake.dailytodo.data.remote.TodoService
import com.red_velvet_cake.dailytodo.utils.Constants
import com.red_velvet_cake.dailytodo.utils.TodoStatus
import java.io.IOException

class PersonalTODOStatusPresenter(
    private val view: PersonalTodoStatus,
    private val todoService: TodoService
) {

    fun setTodoStatus(status: TodoStatus, todoId: String) {
        when (status) {
            TodoStatus.Todo -> updatePersonalTODOStatus(Constants.TODO, todoId)
            TodoStatus.InProgress -> updatePersonalTODOStatus(Constants.IN_PROGRESS, todoId)
            TodoStatus.Done -> updatePersonalTODOStatus(Constants.DONE, todoId)
        }
    }

    private fun updatePersonalTODOStatus(
        newTodoStatus: Int,
        todoId: String,
    ) {
        todoService.updatePersonalTodoStatus(
            todoId,
            newTodoStatus,
            ::onSuccess,
            ::onFailure
        )
    }

    private fun onSuccess(isSuccess: Boolean) {
        view.showLoading(isSuccess)
    }

    private fun onFailure(e: IOException) {
        view.showError(e.message.toString())
    }
}