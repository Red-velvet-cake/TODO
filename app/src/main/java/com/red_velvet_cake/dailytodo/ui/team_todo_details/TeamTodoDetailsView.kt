package com.red_velvet_cake.dailytodo.ui.team_todo_details

interface TeamTodoDetailsView {
    fun showTodoStatusUpdatedFailure(errorMessage: String)
    fun navigateBack()
}