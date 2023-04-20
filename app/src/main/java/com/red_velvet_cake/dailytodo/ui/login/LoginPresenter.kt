package com.red_velvet_cake.dailytodo.ui.login

import com.red_velvet_cake.dailytodo.data.local.LocalDataImpl
import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.remote.auth.AuthenticationServiceImpl

class LoginPresenter(private val view: LoginView) {
    private val authService = AuthenticationServiceImpl()
    private val localDataImpl = LocalDataImpl()

    fun loginUser(username: String, password: String) {
        view.showLoadingState()
        authService.loginAccount(
            username,
            password,
            this::onLoginSuccess,
            this::onLoginFailure
        )
    }

    private fun onLoginSuccess(loginResponse: LoginResponse) {
        view.hideLoadingState()
        if (loginResponse.isSuccess) {
            localDataImpl.setUserToken(loginResponse.loginResponseBody.token)
            view.navigateToHome()
        } else {
            view.showLoginFailedMessage(loginResponse.message.toString())
        }
    }

    private fun onLoginFailure(exception: Exception) {
        view.hideLoadingState()
        view.showLoginFailedMessage(exception.message.toString())
    }

    fun validateInputFields(username: String, password: String): Boolean {
        var isValid = true

        if (username.isBlank()) {
            view.showUsernameError(true)
            isValid = false
        } else {
            view.showUsernameError(false)
        }

        if (password.isBlank()) {
            view.showPasswordError(true)
            isValid = false
        } else {
            view.showPasswordError(false)
        }

        return isValid
    }

    fun navigateToRegister() {
        view.navigateToRegister()
    }
}