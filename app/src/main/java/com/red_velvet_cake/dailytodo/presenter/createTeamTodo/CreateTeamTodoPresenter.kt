package com.red_velvet_cake.dailytodo.presenter.createTeamTodo

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import okio.IOException

class CreateTeamTodoPresenter(
    private val view: ICreateTeamTodoView,
) {
    private val todoService = TodoServiceImpl()

    fun createTeamTodo(
        title: String,
        description: String,
        assignee: String
    ) {
        todoService.createTeamTodo(
            title,
            description,
            assignee,
            ::onCreateTeamTodoSuccess,
            ::onCreateTeamTodoFailure
        )

    }

    private fun onCreateTeamTodoSuccess(createTodoTeamResponse: CreateTodoTeamResponse) {
        view.onCreateTeamTodoSuccess(createTodoTeamResponse)

    }

    private fun onCreateTeamTodoFailure(e: IOException) {
        view.onCreateTeamTodoFailure(e)
    }
}