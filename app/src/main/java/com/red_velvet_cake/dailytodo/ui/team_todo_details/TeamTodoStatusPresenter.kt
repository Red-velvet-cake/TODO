package com.red_velvet_cake.dailytodo.ui.team_todo_details

import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import com.red_velvet_cake.dailytodo.utils.Constants
import com.red_velvet_cake.dailytodo.utils.TodoStatus

class TeamTodoStatusPresenter(
    private val view: TeamTodoDetailsView,
) {
    private val todoServiceImpl = TodoServiceImpl()

    fun setTodoStatus(status: TodoStatus, todoId: String) {
        when (status) {
            TodoStatus.Todo -> updateTeamTodoStatus(Constants.TODO, todoId)
            TodoStatus.InProgress -> updateTeamTodoStatus(Constants.IN_PROGRESS, todoId)
            TodoStatus.Done -> updateTeamTodoStatus(Constants.DONE, todoId)
        }
    }

    private fun updateTeamTodoStatus(
        newTodoStatus: Int,
        todoId: String,
    ) {
        todoServiceImpl.updateTeamTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdateTeamTodoStatusFailure
        )
    }

    private fun onUpdateTeamTodoStatusFailure(errorMessage: String) {
        view.showTodoStatusUpdatedFailure(errorMessage)
    }
}