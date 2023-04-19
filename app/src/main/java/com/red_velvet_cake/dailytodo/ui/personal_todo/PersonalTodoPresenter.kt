package com.red_velvet_cake.dailytodo.ui.personal_todo

import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.remote.api.ApiServiceImpl
import java.io.IOException

class PersonalTodoPresenter(private val view: PersonalTodoView) {

    private val apiService = ApiServiceImpl()

    fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
    ) {
        apiService.updatePersonalTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdatePersonalTodoStatusFailure
        )
    }

    fun getAllTeamTodos() {
        apiService.getAllPersonalTodos(
            ::onGetAllPersonalTodosSuccess,
            ::onGetAllTeamTodosFailure
        )
    }

    private fun onUpdatePersonalTodoStatusFailure(exception: IOException) {
        view.showTodoUpdateFailMessage(exception.message.toString())
    }


    private fun onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse: GetAllPersonalTodosResponse) {
        if (getAllPersonalTodosResponse.value.isEmpty()) {
            view.showEmptyTodoListState()
        }
        view.showTodoList(getAllPersonalTodosResponse.value)
    }

    private fun onGetAllTeamTodosFailure(exception: IOException) {
        view.showLoadTodosFailed()
    }
}