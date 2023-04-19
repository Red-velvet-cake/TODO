package com.red_velvet_cake.dailytodo.ui.createTodo

import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import okio.IOException

interface CreateTodoView {

    fun onCreateTeamTodoFailure(e: IOException)

    fun onCreatePersonalTodoSuccess(createTodoPersonalResponse: CreateTodoPersonalResponse)

    fun onCreatePersonalTodoFailure(e: IOException)

    fun showCreateSuccessMessage()

    fun enableCreateButton()

    fun showCreateFailedMessage(message: String)


}
