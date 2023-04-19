package com.red_velvet_cake.dailytodo.ui.login

interface LoginView {

    fun hideLoadingState()
    fun showLoginFailedMessage(errorMessage: String)
    fun showLoadingState()
    fun showUsernameError(show: Boolean)
    fun showPasswordError(show: Boolean)
    fun navigateToHome()
    fun navigateToRegister()
}