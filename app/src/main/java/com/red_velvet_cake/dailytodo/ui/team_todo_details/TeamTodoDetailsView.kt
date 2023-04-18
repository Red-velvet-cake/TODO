package com.red_velvet_cake.dailytodo.ui.team_todo_details

import java.io.IOException

interface TeamTodoDetailsView {
    fun showTodoStatusUpdatedLoading(isLoading:Boolean)
    fun showTodoStatusUpdatedSuccessfully()
    fun showTodoStatusUpdatedFailure(exception: IOException)
}