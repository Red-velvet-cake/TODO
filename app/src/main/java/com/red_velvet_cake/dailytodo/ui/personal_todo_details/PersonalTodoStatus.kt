package com.red_velvet_cake.dailytodo.ui.personal_todo_details

interface PersonalTodoStatus {
    fun showLoading(status: Boolean)
    fun showError(errorMessage: String)
}