package com.red_velvet_cake.dailytodo.ui.register

import com.red_velvet_cake.dailytodo.data.local.LocalDataImpl
import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.data.remote.auth.AuthenticationServiceImpl

class RegisterPresenter(
    private val view: RegisterView,
) {

    private val authService = AuthenticationServiceImpl()
    private val localDataImpl = LocalDataImpl()
    private var username: String = ""
    private var password: String = ""

    private fun registerAccount(username: String, password: String, teamId: String) {
        this.username = username
        this.password = password
        authService.registerAccount(
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

    private fun onRegisterAccountFailure(exception: Exception) {
        view.showRegisterFailedMessage(exception.message.toString())
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
        localDataImpl.saveUserName(username)
        authService.loginAccount(
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
            localDataImpl.setUserToken(loginResponse.loginResponseBody.token)
        } else {
            view.showLoginFailedMessage(loginResponse.message.toString())
        }
    }

    private fun onLoginAccountFailure(exception: Exception) {
        view.showLoginFailedMessage(exception.message.toString())
    }

    fun navigateToLogin() {
        view.navigateToLogin()
    }

}