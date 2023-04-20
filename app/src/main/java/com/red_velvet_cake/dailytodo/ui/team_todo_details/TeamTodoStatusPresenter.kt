package com.red_velvet_cake.dailytodo.ui.team_todo_details

import com.red_velvet_cake.dailytodo.data.remote.todo_service.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.remote.util.CustomException

class TeamTodoStatusPresenter(
    private val view: TeamTodoDetailsView,
) {
    private val apiService = TodoServiceImpl()
    fun updateTeamTodoStatus(
        newTodoStatus: Int,
        todoId: String,
    ) {
        apiService.updateTeamTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdateTeamTodoStatusFailure
        )
    }

    private fun onUpdateTeamTodoStatusFailure(exception: Exception) {
        when (exception) {
            is CustomException.UnauthorizedUserException -> {
                view.navigateBack()
            }

            else -> {
                view.showTodoStatusUpdatedFailure(exception.message.toString())
            }
        }
    }
}