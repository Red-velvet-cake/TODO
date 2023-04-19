package com.red_velvet_cake.dailytodo.ui.createTodo

import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl

class CreateTodoPresenter(val view: CreateTodoView) {

    private val todoServiceImpl = TodoServiceImpl()
    private var title: String = ""
    private var description: String = ""
    private var assignee: String = ""

    private fun createTeamTodoSuccess(
        title: String,
        description: String,
        assignee: String
    ) {
        this.title = title
        this.description = description
        this.assignee = assignee

        todoServiceImpl.createTeamTodo(
            title,
            description,
            assignee,
            ::onCreateTeamTodoSuccess,
            ::onCreateTeamTodoFailure
        )
    }

    private fun createPersonalTodo(
        title: String,
        description: String
    ) {

        this.title = title
        this.description = description

        todoServiceImpl.createPersonalTodo(
            title,
            description,
            ::onCreatePersonalTodoSuccess,
            ::onCreatePersonalTodoFailure
        )
    }

    private fun onCreateTeamTodoSuccess(createTodoTeamResponse: CreateTodoTeamResponse) {
        view.enableCreateButton()
        if (createTodoTeamResponse.isSuccess) {
            view.showCreateSuccessMessage()
            createTeamTodoSuccess(title, description, assignee)
        } else {
            view.showCreateFailedMessage(createTodoTeamResponse.message)
        }
    }

    private fun onCreateTeamTodoFailure(errorMessage: String) {
        view.onCreateTeamTodoFailure(errorMessage)
    }

    private fun onCreatePersonalTodoSuccess(createTodoPersonalResponse: CreateTodoPersonalResponse) {
        view.onCreatePersonalTodoSuccess(createTodoPersonalResponse)
    }

    private fun onCreatePersonalTodoFailure(errorMessage: String) {
        view.onCreateTeamTodoFailure(errorMessage)
    }
}