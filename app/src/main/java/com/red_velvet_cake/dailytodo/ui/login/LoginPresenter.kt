package com.red_velvet_cake.dailytodo.ui.login

import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import okio.IOException

class LoginPresenter(private val view: LoginView) {
    private val todoServiceImpl = TodoServiceImpl()

    fun loginUser(username: String, password: String) {
        view.showLoadingIndicator(true)
        todoServiceImpl.loginUser(
            username,
            password,
            this::onSuccess,
            this::onFailure
        )
    }

    private fun onSuccess(loginResponse: LoginResponse) {
        if (loginResponse.isSuccess){
            view.showLoadingIndicator(false)
            view.onSuccess(loginResponse)
            navigateToHome()
        } else {
            onFailure(loginResponse.message as Exception)
        }
    }

    private fun onFailure(exception: Exception) {
        view.showLoadingIndicator(false)
        view.onFailure(exception)
    }
    private fun navigateToHome() {
        view.navigateToHome()
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
}