package com.red_velvet_cake.dailytodo.presenter.create_team_todo

import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import okio.IOException

class CreateTeamTodoPresenter(
    private val view: CreateTeamTodoView,
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