package com.red_velvet_cake.dailytodo.presenter.createTodo

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import okio.IOException

class CreateTodoPresenter(
    private val view: ICreateTodoView,
) {
    private val todoService = TodoServiceImpl()

    fun createPersonalTodo(title: String, description: String) {
        todoService.createPersonalTodo(
            title,
            description,
            ::onCreatePersonalTodoSuccess,
            ::onCreatePersonalTodoFailure
        )
    }

    fun createTeamTodo(title: String, description: String, assignee: String) {
        todoService.createTeamTodo(
            title,
            description,
            assignee,
            ::onCreateTeamTodoSuccess,
            ::onCreateTeamTodoFailure
        )
    }

    private fun onCreatePersonalTodoSuccess(isSuccess: Boolean) {
        view.onCreatePersonalTodoSuccess(isSuccess)
    }

    private fun onCreatePersonalTodoFailure(e: IOException) {
        view.onCreatePersonalTodoFailure(e)
    }

    private fun onCreateTeamTodoSuccess(isSuccess: Boolean) {
        view.onCreateTeamTodoSuccess(isSuccess)
    }

    private fun onCreateTeamTodoFailure(e: IOException) {
        view.onCreateTeamTodoFailure(e)
    }
}