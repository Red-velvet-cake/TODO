package com.red_velvet_cake.dailytodo.ui.team_todo

import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.data.remote.todo_service.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.remote.util.CustomException

class TeamTodoPresenter(private val view: TeamTodoView) {

    private val todoServiceImpl = TodoServiceImpl()

    fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
    ) {
        todoServiceImpl.updateTeamTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdateTeamTodoStatusFailure
        )
    }

    fun getAllTeamTodos() {
        todoServiceImpl.getAllTeamTodos(
            ::onGetAllTeamTodosSuccess,
            ::onGetAllTeamTodosFailure
        )
    }

    private fun onUpdateTeamTodoStatusFailure(exception: Exception) {
        when (exception) {
            is CustomException.UnauthorizedUserException -> {
                view.navigateBack()
            }

            else -> {
                view.showTodoUpdateFailMessage(exception.message.toString())
            }
        }
    }

    private fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        if (getAllTeamTodosResponse.value.isEmpty()) {
            view.showEmptyTodoListState()
        }
        view.showTodoList(getAllTeamTodosResponse.value)
    }

    private fun onGetAllTeamTodosFailure(exception: Exception) {
        when (exception) {
            is CustomException.UnauthorizedUserException -> {
                view.navigateBack()
            }

            else -> {
                view.showLoadTodosFailed()
            }
        }
    }

    fun navigateToTodoDetails(todo: TeamTodo) {
        view.navigateToTodoDetails(todo)
    }
}