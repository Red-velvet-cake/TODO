package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import com.red_velvet_cake.dailytodo.data.model.UpdatePersonalStatusResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import com.red_velvet_cake.dailytodo.utils.Constants
import com.red_velvet_cake.dailytodo.utils.TodoStatus
import java.io.IOException

class PersonalTodoDetailsPresenter(
    private val view: PersonalTodoDetailsView,
) {

    private val todoService = TodoServiceImpl()

    fun setTodoStatus(status: TodoStatus, todoId: String) {
        view.showTodoStatusUpdatedLoading(true)
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
        view.showTodoStatusUpdatedLoading(!updatePersonalStatusResponse.isSuccess)
        view.showTodoStatusUpdatedSuccessfully()
    }

    private fun onUpdatePersonalTodoStatusFailure(exception: IOException) {
        view.showTodoStatusUpdatedLoading(false)
        view.showTodoStatusUpdatedFailure(exception)
    }
}