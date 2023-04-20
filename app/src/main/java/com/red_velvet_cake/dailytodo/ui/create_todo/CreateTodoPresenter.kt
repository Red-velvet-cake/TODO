package com.red_velvet_cake.dailytodo.ui.create_todo

import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import com.red_velvet_cake.dailytodo.data.model.CreateTodoTeamResponse
import com.red_velvet_cake.dailytodo.data.remote.todo_service.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.remote.util.CustomException

class CreateTodoPresenter(val view: CreateTodoView) {
    private val todoServiceImpl = TodoServiceImpl()

    private fun createTeamTodo(
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
            view.navigateBack()
        } else {
            view.showCreateFailedMessage(createTodoTeamResponse.message)
        }
    }

    private fun onCreateTeamTodoFailure(exception: Exception) {
        when (exception) {
            is CustomException.UnauthorizedUserException -> {
                view.navigateBack()
            }

            else -> {
                view.showTryAgain()
                view.showCreateFailedMessage(exception.message.toString())
            }
        }
    }

    private fun onCreatePersonalTodoSuccess(createTodoPersonalResponse: CreateTodoPersonalResponse) {
        view.enableCreateButton()
        if (createTodoPersonalResponse.isSuccess) {
            view.showCreateSuccessMessage()
            view.navigateBack()
        } else {
            view.showCreateFailedMessage(createTodoPersonalResponse.message)
        }
    }

    private fun onCreatePersonalTodoFailure(exception: Exception) {
        when (exception) {
            is CustomException.UnauthorizedUserException -> {
                view.navigateBack()
            }

            else -> {
                view.showTryAgain()
                view.showCreateFailedMessage(exception.message.toString())
            }
        }
    }

    fun clickCreateTodoTeamButton(
        title: String,
        description: String,
        assignee: String
    ) {
        if (isTitleDescriptionAndAssigneeEmpty(title, description, assignee)) {
            view.disableCreateButtonWithLoading()
            createTeamTodo(title, description, assignee)
        } else {
            view.showInvalidTodoDetailsError()
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
            view.showInvalidTodoDetailsError()
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