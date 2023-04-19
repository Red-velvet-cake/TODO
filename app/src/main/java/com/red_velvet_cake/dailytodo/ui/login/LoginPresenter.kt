package com.red_velvet_cake.dailytodo.ui.login

import com.red_velvet_cake.dailytodo.data.local.SharedPrefs
import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl

class LoginPresenter(private val view: LoginView) {
    private val todoServiceImpl = TodoServiceImpl()

    fun loginUser(username: String, password: String) {
        view.showLoadingState()
        todoServiceImpl.loginUser(
            username,
            password,
            this::onSuccess,
            this::onFailure
        )
    }

    private fun onSuccess(loginResponse: LoginResponse) {
        view.hideLoadingState()
        if (loginResponse.isSuccess) {
            SharedPrefs.token = loginResponse.loginResponseBody.token
            view.navigateToHome()
        } else {
            view.showLoginFailedMessage(loginResponse.message.toString())
        }
    }

    private fun onFailure(exception: Exception) {
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