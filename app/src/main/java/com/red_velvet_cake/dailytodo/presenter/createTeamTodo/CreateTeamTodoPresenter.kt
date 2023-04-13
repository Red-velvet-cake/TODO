package com.red_velvet_cake.dailytodo.presenter.createTeamTodo

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.model.PersonalTODORequest
import com.red_velvet_cake.dailytodo.presenter.createPersonalTodo.ICreateTeamTodoView
import okio.IOException

class CreateTeamTodoPresenter(
    private val view: ICreateTeamTodoView,
) {
    private val todoService = TodoServiceImpl()

    fun createTeamTodo(personalTodoRequest: PersonalTODORequest) {
        todoService.createPersonalTodo(
            personalTodoRequest,
            ::onCreateTeamTodoSuccess,
            ::onCreateTeamTodoFailure
        )
    }

    private fun onCreateTeamTodoSuccess(isSuccess: Boolean) {
        view.onCreateTeamTodoSuccess(isSuccess)
    }

    private fun onCreateTeamTodoFailure(e: IOException) {
        view.onCreateTeamTodoFailure(e)
    }
}