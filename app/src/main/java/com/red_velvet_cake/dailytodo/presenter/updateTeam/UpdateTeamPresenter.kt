package com.red_velvet_cake.dailytodo.presenter.updateTeam

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.model.UpdateTeamTodoResponse
import java.io.IOException

class UpdateTeamPresenter(private val view: IMainView) {

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

    private fun onUpdateTeamTodoStatusSuccess(updateTeamStatusResponse: UpdateTeamTodoResponse) {
        view.onUpdateTeamTodoStatusSuccess(updateTeamStatusResponse)
    }

    private fun onUpdateTeamTodoStatusFailure(exception: IOException) {
        view.onUpdateTeamTodoStatusFailure(exception)
    }
}

