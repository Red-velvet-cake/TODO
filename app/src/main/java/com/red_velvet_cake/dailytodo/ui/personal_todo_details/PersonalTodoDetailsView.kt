package com.red_velvet_cake.dailytodo.ui.personal_todo_details

interface PersonalTodoDetailsView {
    fun showTodoStatusUpdatedFailure(errorMessage: String)
    fun navigateBack()
}