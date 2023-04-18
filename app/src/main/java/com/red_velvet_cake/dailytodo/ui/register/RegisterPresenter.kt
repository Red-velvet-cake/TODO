package com.red_velvet_cake.dailytodo.ui.register

import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoService
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import com.red_velvet_cake.dailytodo.utils.RegisterFormError
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
        view.enableView()
        if (registerAccountResponse.isSuccess) {
            view.showToast("Account created successfully.")
            loginUsingCredentials(username, password)
        } else {
            view.showToast(registerAccountResponse.message)
        }
    }

    private fun onRegisterAccountFailure(exception: IOException) {
        view.showToast(exception.message.toString())
    }

    fun clickRegisterButton(
        username: String,
        password: String,
        confirmPassword: String,
        teamId: String
    ) {
        if (validateForm(username, password, confirmPassword)) {
            view.disableView()
            registerAccount(username.trim(), password.trim(), teamId)
        }
    }

    private fun validateForm(username: String, password: String, confirmPassword: String): Boolean {
        return when {
            !validateUsername(username) -> {
                view.showValidationError(RegisterFormError.USERNAME_INVALID)
                false
            }

            !validatePassword(password) -> {
                view.showValidationError(RegisterFormError.PASSWORD_INVALID)
                false
            }

            !validateConfirmPassword(password, confirmPassword) -> {
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
        todoService.loginUser(
            username,
            password,
            ::onLoginAccountSuccess,
            ::onLoginAccountFailure
        )
    }

    private fun onLoginAccountSuccess(loginResponse: LoginResponse) {
        view.enableView()
        if (loginResponse.isSuccess) {
            view.navigateToHome()
        } else {
            view.showToast(loginResponse.message)
        }
    }

    private fun onLoginAccountFailure(exception: IOException) {
        view.showToast(exception.message.toString())
    }

}