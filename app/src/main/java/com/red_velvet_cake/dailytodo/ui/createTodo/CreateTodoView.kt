package com.red_velvet_cake.dailytodo.ui.createTodo

import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse

interface CreateTodoView {

    fun onCreateTeamTodoFailure(errorMessage: String)

    fun onCreatePersonalTodoSuccess(createTodoPersonalResponse: CreateTodoPersonalResponse)

    fun onCreatePersonalTodoFailure(errorMessage: String)

    fun showCreateSuccessMessage()

    fun enableCreateButton()

    fun showCreateFailedMessage(errorMessage: String)


}
