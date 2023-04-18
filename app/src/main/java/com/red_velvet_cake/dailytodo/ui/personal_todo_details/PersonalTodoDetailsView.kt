package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import java.io.IOException

interface PersonalTodoDetailsView {
    fun showTodoStatusUpdatedFailure(exception: IOException)
}