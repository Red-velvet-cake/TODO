package com.red_velvet_cake.dailytodo.ui.team_todo_details

import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import com.red_velvet_cake.dailytodo.utils.Constants
import com.red_velvet_cake.dailytodo.utils.TodoStatus
import java.io.IOException

class TeamTODOStatusPresenter(
    private val view: TeamTodoDetailsView,
) {
    private val todoServiceImpl = TodoServiceImpl()

    fun setTodoStatus(status: TodoStatus, todoId: String) {
        when (status) {
            TodoStatus.Todo -> updateTeamTODOStatus(Constants.TODO, todoId)
            TodoStatus.InProgress -> updateTeamTODOStatus(Constants.IN_PROGRESS, todoId)
            TodoStatus.Done -> updateTeamTODOStatus(Constants.DONE, todoId)
        }
    }

    private fun updateTeamTODOStatus(
        newTodoStatus: Int,
        todoId: String,
    ) {
        todoServiceImpl.updateTeamTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdateTeamTodoStatusFailure
        )
    }

    private fun onUpdateTeamTodoStatusFailure(exception: IOException) {
        view.showTodoStatusUpdatedFailure(exception.message.toString())
    }
}