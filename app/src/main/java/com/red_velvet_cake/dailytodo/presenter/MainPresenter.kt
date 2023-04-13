package com.red_velvet_cake.dailytodo.presenter

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import java.io.IOException


class MainPresenter(private val view: IMainView) {

    private val todoServiceImpl = TodoServiceImpl()

    fun updatePersonalTodoStatus(
        todoId: String, newTodoStatus: Int
    ) {
        todoServiceImpl.updatePersonalTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdatePersonalTodoStatusSuccess,
            ::onUpdatePersonalTodoStatusFailure
        )
    }

    fun responseTeamTodo() {
        todoServiceImpl.getAllTeamTodos(
            ::onGetAllTeamTodosSuccess,
            ::onGetAllTeamTodosFailure
        )
    }

    private fun onUpdatePersonalTodoStatusSuccess(updatePersonalStatusResponse: UpdatePersonalStatusResponse) {
        view.onUpdatePersonalTodoStatusSuccess(updatePersonalStatusResponse)
    }

    private fun onUpdatePersonalTodoStatusFailure(exception: IOException) {
        view.onUpdatePersonalTodoStatusFailure(exception)
    }

    private fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        view.onGetAllTeamTodosSuccess(getAllTeamTodosResponse)
    }

    private fun onGetAllTeamTodosFailure(exception: IOException) {
        view.onGetAllTeamTodosFailure(exception)
    }
}