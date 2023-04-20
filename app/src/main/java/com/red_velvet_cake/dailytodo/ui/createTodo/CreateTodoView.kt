package com.red_velvet_cake.dailytodo.ui.createTodo

interface CreateTodoView {

    fun onCreateTeamTodoFailure(errorMessage: String)

    fun onCreatePersonalTodoFailure(errorMessage: String)

    fun showCreateSuccessMessage()

    fun enableCreateButton()

    fun showCreateFailedMessage(errorMessage: String)

    fun disableCreateButtonWithLoading()


}
