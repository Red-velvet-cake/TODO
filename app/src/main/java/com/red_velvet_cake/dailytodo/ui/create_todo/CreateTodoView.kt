package com.red_velvet_cake.dailytodo.ui.create_todo

interface CreateTodoView {
    fun showCreateSuccessMessage()
    fun enableCreateButton()
    fun showCreateFailedMessage(errorMessage: String)
    fun disableCreateButtonWithLoading()
    fun navigateBack()
    fun showTryAgain()
    fun showInvalidTodoDetailsError()
    fun stopLoadingButton()
}
