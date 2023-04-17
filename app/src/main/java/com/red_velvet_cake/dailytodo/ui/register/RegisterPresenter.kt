package com.red_velvet_cake.dailytodo.ui.register

import com.red_velvet_cake.dailytodo.BuildConfig
import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoService
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import com.red_velvet_cake.dailytodo.utils.RegisterFormError
import java.io.IOException

class RegisterPresenter(
    private val view: RegisterView,
) {

    private val service: TodoService = TodoServiceImpl()

    private fun registerAccount(username: String, password: String) {
        service.registerAccount(
            username,
            password,
            BuildConfig.TEAM_ID,
            { response -> handleRegisterAccountSuccess(response, username, password) },
            ::handleRegisterAccountFailure
        )
    }

    private fun handleRegisterAccountSuccess(
        registerAccountResponse: RegisterAccountResponse,
        username: String,
        password: String
    ) {
        view.showRegisterButtonEnabledState()
        if (registerAccountResponse.isSuccess) {
            view.showToast("Account created successfully.")
            loginUsingCredentials(username, password)
        } else {
            view.showToast(registerAccountResponse.message)
        }
    }

    private fun handleRegisterAccountFailure(exception: IOException) {
        view.showToast(exception.message.toString())
    }

    fun clickRegisterButton(username: String, password: String, confirmPassword: String) {
        if (validateForm(username, password, confirmPassword)) {
            view.showRegisterButtonLoadingState()
            registerAccount(username.trim(), password.trim())
        }
    }

    private fun validateForm(username: String, password: String, confirmPassword: String): Boolean {
        val trimmedUsername = username.trim()
        val trimmedPassword = password.trim()
        val trimmedConfirmPassword = confirmPassword.trim()

        return when {
            !validateUsername(trimmedUsername) -> {
                view.showValidationError(RegisterFormError.USERNAME_INVALID)
                false
            }

            !validatePassword(trimmedPassword) -> {
                view.showValidationError(RegisterFormError.PASSWORD_INVALID)
                false
            }

            !validateConfirmPassword(trimmedPassword, trimmedConfirmPassword) -> {
                view.showValidationError(RegisterFormError.CONFIRM_PASSWORD_NOT_MATCHING)
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
        service.loginUser(
            username,
            password,
            ::handleLoginSuccess,
            ::handleLoginFailure
        )
    }

    private fun handleLoginSuccess(loginResponse: LoginResponse) {
        view.showRegisterButtonEnabledState()
        if (loginResponse.isSuccess) {
            view.navigateToHome()
        } else {
            view.showToast(loginResponse.message)
        }
    }

    private fun handleLoginFailure(exception: IOException) {
        view.showToast(exception.message.toString())
    }

}