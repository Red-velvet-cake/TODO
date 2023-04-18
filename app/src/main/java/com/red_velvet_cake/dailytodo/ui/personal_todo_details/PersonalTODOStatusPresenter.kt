package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import com.red_velvet_cake.dailytodo.data.model.UpdatePersonalStatusResponse
import com.red_velvet_cake.dailytodo.data.model.UpdateTeamTodoStatusResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoService
import com.red_velvet_cake.dailytodo.utils.Constants
import com.red_velvet_cake.dailytodo.utils.ResponseStatus
import com.red_velvet_cake.dailytodo.utils.TodoStatus
import java.io.IOException

class PersonalTODOStatusPresenter(
    private val view: PersonalTodoStatus,
    private val todoService: TodoService
) {

    fun setTodoStatus(status: TodoStatus, todoId: String) {
        view.handleResponseStatus(ResponseStatus.Loading)
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
            ::onUpdatePersonalTodoStatusSuccess,
            ::onUpdatePersonalTodoStatusFailure
        )
    }

    private fun onUpdatePersonalTodoStatusSuccess(updatePersonalStatusResponse: UpdatePersonalStatusResponse) {
        view.handleResponseStatus(ResponseStatus.Success(updatePersonalStatusResponse))
    }

    private fun onUpdatePersonalTodoStatusFailure(exception: IOException) {
        view.handleResponseStatus(ResponseStatus.Error(exception.message.toString()))
    }
}