package com.red_velvet_cake.dailytodo.ui.register

import android.util.Log
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
        view.hideLoading()
        if (registerAccountResponse.isSuccess) {
            view.showToast(registerAccountResponse.message ?: "Account created successfully")
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
            view.showLoading()
            registerAccount(username, password)
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
        service.loginUser(
            username,
            password,
            ::handleLoginUsingCredentialsSuccess,
            ::handleLoginUsingCredentialsFailure
        )
    }

    private fun handleLoginUsingCredentialsSuccess(loginResponse: LoginResponse) {
        Log.d("RegisterPresenter", loginResponse.toString())
        view.hideLoading()
        if (loginResponse.isSuccess) {
            view.navigateToHome()
        } else {
            view.showToast(loginResponse.message)
        }
    }

    private fun handleLoginUsingCredentialsFailure(exception: IOException) {
        Log.d("RegisterPresenter", exception.toString())
        view.showToast(exception.message.toString())
    }

}