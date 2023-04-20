package com.red_velvet_cake.dailytodo.ui.register

interface RegisterView {

    fun navigateToHome()

    fun navigateToLogin()

    fun showUsernameValidationError()

    fun showPasswordValidationError()

    fun showConfirmPasswordValidationError()

    fun showRegisterSuccessMessage()

    fun showRegisterFailedMessage(message: String)

    fun showLoginFailedMessage(message: String)

    fun disableRegisterButtonWithLoading()

    fun enableRegisterButton()

}