package com.red_velvet_cake.dailytodo.presenter.update_team_status

import com.red_velvet_cake.dailytodo.data.model.UpdateTeamTodoStatusResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import java.io.IOException

class UpdateTeamStatusPresenter(private val view: UpdateTeamTodoStatusView) {

    private val todoServiceImpl = TodoServiceImpl()

    fun updateTeamTodoStatus(
        todoId: String, newTodoStatus: Int
    ) {
        todoServiceImpl.updateTeamTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdateTeamTodoStatusSuccess,
            ::onUpdateTeamTodoStatusFailure
        )
    }

    private fun onUpdateTeamTodoStatusSuccess(updateTeamStatusResponse: UpdateTeamTodoStatusResponse) {
        view.onUpdateTeamTodoStatusSuccess(updateTeamStatusResponse)
    }

    private fun onUpdateTeamTodoStatusFailure(exception: IOException) {
        view.onUpdateTeamTodoStatusFailure(exception)
    }
}

