package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import java.io.IOException

interface PersonalTodoDetailsView {
    fun showTodoStatusUpdatedLoading(isLoading:Boolean)
    fun showTodoStatusUpdatedSuccessfully()
    fun showTodoStatusUpdatedFailure(exception: IOException)
}