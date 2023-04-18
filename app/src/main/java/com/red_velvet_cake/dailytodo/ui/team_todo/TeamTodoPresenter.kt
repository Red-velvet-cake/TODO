package com.red_velvet_cake.dailytodo.ui.team_todo

import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.UpdateTeamTodoStatusResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import java.io.IOException

class TeamTodoPresenter(private val view: TeamTodoView) {

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
        view.showToast(ToastMessage.TODO_UPDATED_SUCCESSFULLY)
    }

    private fun onUpdateTeamTodoStatusFailure(exception: IOException) {
        view.showupdateTodoStatusFailed(exception.message.toString())
    }

    private fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        if (getAllTeamTodosResponse.value.isEmpty()) {
            view.showEmptyTodoList()
        }
        view.showTodoList(getAllTeamTodosResponse.value)
    }

    private fun onGetAllTeamTodosFailure(exception: IOException) {
        view.showLoadingTodoListFailed()
    }
}