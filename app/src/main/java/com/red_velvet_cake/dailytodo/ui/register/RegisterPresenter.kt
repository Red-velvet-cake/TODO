package com.red_velvet_cake.dailytodo.ui.register

import com.red_velvet_cake.dailytodo.data.local.SharedPrefs
import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoService
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl

class RegisterPresenter(
    private val view: RegisterView,
) {

    private val todoService: TodoService = TodoServiceImpl()
    private var username: String = ""
    private var password: String = ""

    private fun registerAccount(username: String, password: String, teamId: String) {
        this.username = username
        this.password = password
        todoService.registerAccount(
            username,
            password,
            teamId,
            ::onRegisterAccountSuccess,
            ::onRegisterAccountFailure
        )
    }

    private fun onRegisterAccountSuccess(registerAccountResponse: RegisterAccountResponse) {
        view.enableRegisterButton()
        if (registerAccountResponse.isSuccess) {
            view.showRegisterSuccessMessage()
            loginUsingCredentials(username, password)
        } else {
            view.showRegisterFailedMessage(registerAccountResponse.message)
        }
    }

    private fun onRegisterAccountFailure(errorMessage: String) {
        view.showRegisterFailedMessage(errorMessage)
    }

    fun clickRegisterButton(
        username: String,
        password: String,
        confirmPassword: String,
        teamId: String
    ) {
        if (validateForm(username, password, confirmPassword)) {
            view.disableRegisterButtonWithLoading()
            registerAccount(username, password, teamId)
        }
    }

    private fun validateForm(username: String, password: String, confirmPassword: String): Boolean {
        if (!isValidUsername(username)) {
            view.showUsernameValidationError()
            return false
        }

        if (!isValidPassword(password)) {
            view.showPasswordValidationError()
            return false
        }

        if (!isValidConfirmPassword(password, confirmPassword)) {
            view.showConfirmPasswordValidationError()
            return false
        }

        return true
    }

    private fun isValidUsername(username: String): Boolean {
        return username.isNotEmpty() && username.length >= 4
    }

    private fun isValidPassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 8
    }

    private fun isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
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
            SharedPrefs.token = loginResponse.loginResponseBody.token
        } else {
            view.showLoginFailedMessage(loginResponse.message.toString())
        }
    }

    private fun onLoginAccountFailure(errorMessage: String) {
        view.showLoginFailedMessage(errorMessage)
    }

    fun navigateToLogin() {
        view.navigateToLogin()
    }

}