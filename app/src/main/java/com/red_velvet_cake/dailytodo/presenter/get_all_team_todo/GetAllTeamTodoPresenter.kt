package com.red_velvet_cake.dailytodo.presenter.get_all_team_todo

import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import java.io.IOException

class GetAllTeamTodoPresenter(private val view: GetAllTeamTodoView) {

    private val todoServiceImpl = TodoServiceImpl()

    fun updatePersonalTodoStatus(
        todoId: String,
        newTodoStatus: Int,
    ) {
        todoServiceImpl.getAllTeamTodos(
            ::onUpdatePersonalTodoStatusSuccess,
            ::onUpdatePersonalTodoStatusFailure
        )
    }

    private fun onUpdatePersonalTodoStatusSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        view.onGetAllTeamTodosSuccess(getAllTeamTodosResponse)
    }

    private fun onUpdatePersonalTodoStatusFailure(exception: IOException) {
        view.onGetAllTeamTodosFailure(exception)
    }
}

