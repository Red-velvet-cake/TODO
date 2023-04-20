package com.red_velvet_cake.dailytodo.ui.create_todo

import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import com.red_velvet_cake.dailytodo.data.remote.todo_service.TodoServiceImpl

class CreateTodoPresenter(val view: CreateTodoView) {
    private val todoServiceImpl = TodoServiceImpl()

    private fun createTeamTodoSuccess(
        title: String,
        description: String,
        assignee: String
    ) {
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
        } else {
            view.showCreateFailedMessage(createTodoTeamResponse.message)
        }
    }

    private fun onCreateTeamTodoFailure(errorMessage: String) {
        view.onCreateTeamTodoFailure(errorMessage)
    }

    private fun onCreatePersonalTodoSuccess(createTodoPersonalResponse: CreateTodoPersonalResponse) {
        view.enableCreateButton()
        if (createTodoPersonalResponse.isSuccess) {
            view.showCreateSuccessMessage()
        } else {
            view.showCreateFailedMessage(createTodoPersonalResponse.message)
        }
    }

    private fun onCreatePersonalTodoFailure(errorMessage: String) {
        view.onCreateTeamTodoFailure(errorMessage)
    }

    fun clickCreateTodoTeamButton(
        title: String,
        description: String,
        assignee: String
    ) {
        if (isTitleDescriptionAndAssigneeEmpty(title, description, assignee)) {
            view.disableCreateButtonWithLoading()
            createTeamTodoSuccess(title, description, assignee)
        } else {
            view.showCreateFailedMessage("Please fill in all fields")
        }
    }

    fun clickCreateTodoPersonalButton(
        title: String,
        description: String
    ) {
        if (isTitleAndDescriptionEmpty(title, description)) {
            view.disableCreateButtonWithLoading()
            createPersonalTodo(title, description)
        } else {
            view.showCreateFailedMessage("Please fill in all fields")
        }
    }

    private fun isTitleAndDescriptionEmpty(
        title: String,
        description: String
    ) = title.isNotEmpty() && description.isNotEmpty()

    private fun isTitleDescriptionAndAssigneeEmpty(
        title: String,
        description: String,
        assignee: String
    ) = title.isNotEmpty() && description.isNotEmpty() && assignee.isNotEmpty()
}