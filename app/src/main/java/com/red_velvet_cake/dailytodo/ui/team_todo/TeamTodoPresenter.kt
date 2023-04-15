package com.red_velvet_cake.dailytodo.ui.team_todo

import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.UpdateTeamTodoStatusResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import java.io.IOException

class TeamTodoPresenter(private val view: TeamTodo) {

    private val todoServiceImpl = TodoServiceImpl()

    fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
    ) {
        todoServiceImpl.updateTeamTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdateTeamTodoStatusSuccess,
            ::onUpdateTeamTodoStatusFailure
        )
    }

    fun getAllTeamTodos() {
        todoServiceImpl.getAllTeamTodos(
            ::onGetAllTeamTodosSuccess,
            ::onGetAllTeamTodosFailure
        )
    }


    private fun onUpdateTeamTodoStatusSuccess(updateTeamTodoStatusResponse: UpdateTeamTodoStatusResponse) {
        view.onUpdateTeamTodoStatusSuccess(updateTeamTodoStatusResponse)
    }

    private fun onUpdateTeamTodoStatusFailure(exception: IOException) {
        view.onUpdateTeamTodoStatusFailure(exception)
    }

    private fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        view.onGetAllTeamTodosSuccess(getAllTeamTodosResponse)
    }

    private fun onGetAllTeamTodosFailure(exception: IOException) {
        view.onGetAllTeamTodosFailure(exception)
    }
}