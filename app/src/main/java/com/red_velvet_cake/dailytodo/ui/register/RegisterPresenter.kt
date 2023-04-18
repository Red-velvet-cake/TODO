package com.red_velvet_cake.dailytodo.ui.register

import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoService
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import java.io.IOException

class RegisterPresenter(
    private val view: RegisterView,
) {

    private val todoService: TodoService = TodoServiceImpl()

    private fun registerAccount(username: String, password: String, teamId: String) {
        todoService.registerAccount(
            username,
            password,
            teamId,
            { response -> onRegisterAccountSuccess(response, username, password) },
            ::onRegisterAccountFailure
        )
    }

    private fun onRegisterAccountSuccess(
        registerAccountResponse: RegisterAccountResponse,
        username: String,
        password: String
    ) {
        view.enableRegisterButton()
        if (registerAccountResponse.isSuccess) {
            view.showRegisterSuccessMessage()
            loginUsingCredentials(username, password)
        } else {
            view.showRegisterFailedMessage(registerAccountResponse.message)
        }
    }

    private fun onRegisterAccountFailure(exception: IOException) {
        exception.message?.let { view.showRegisterFailedMessage(it) }
    }

    fun clickRegisterButton(
        username: String,
        password: String,
        confirmPassword: String,
        teamId: String
    ) {
        if (validateForm(username, password, confirmPassword)) {
            view.disableRegisterButtonWithLoading()
            registerAccount(username.trim(), password.trim(), teamId)
        }
    }

    private fun validateForm(username: String, password: String, confirmPassword: String): Boolean {
        return when {
            !validateUsername(username) -> {
                view.showUsernameValidationError()
                false
            }

            !validatePassword(password) -> {
                view.showPasswordValidationError()
                false
            }

            !validateConfirmPassword(password, confirmPassword) -> {
                view.showConfirmPasswordValidationError()
                false
            }

            else -> true
        }
    }

    private fun validateUsername(username: String): Boolean {
        return username.isNotEmpty() && username.length >= 4
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 8
    }

    private fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    private fun loginUsingCredentials(username: String, password: String) {
        todoService.loginUser(
            username,
            password,
            ::onLoginAccountSuccess,
            ::onLoginAccountFailure
        )
    }

    private fun onLoginAccountSuccess(loginResponse: LoginResponse) {
        view.enableRegisterButton()
        if (loginResponse.isSuccess) {
            view.navigateToHome()
        } else {
            view.showLoginFailedMessage(loginResponse.message)
        }
    }

    private fun onLoginAccountFailure(exception: IOException) {
        view.showLoginFailedMessage(exception.message.toString())
    }

}