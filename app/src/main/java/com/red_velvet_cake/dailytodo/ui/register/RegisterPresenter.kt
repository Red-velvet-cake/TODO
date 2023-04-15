package com.red_velvet_cake.dailytodo.ui.register

import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoService
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import java.io.IOException

class RegisterPresenter(
    private val view: RegisterView,
) {

    private val service: TodoService = TodoServiceImpl()

    fun registerAccount(username: String, password: String, teamId: String) {
        service.registerAccount(
            username,
            password,
            teamId,
            ::handleRegisterAccountSuccess,
            ::handleRegisterAccountFailure
        )
    }

    private fun handleRegisterAccountSuccess(registerAccountResponse: RegisterAccountResponse) {
        view.handleRegisterAccountSuccess(registerAccountResponse)
    }

    private fun handleRegisterAccountFailure(exception: IOException) {
        view.handleRegisterAccountFailure(exception)
    }

    fun handleRegisterButtonClick() {
        view.handleRegisterButtonClick()
    }

    fun handleLoginButtonClick() {
        view.handleLoginButtonClick()
    }

    fun validateUsername(username: String): Boolean {
        return view.validateUsername(username)
    }

    fun validatePassword(password: String): Boolean {
        return view.validatePassword(password)
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        return view.validateConfirmPassword(password, confirmPassword)
    }

    fun showUsernameValidationError(message: String) {
        view.showUsernameValidationError(message)
    }

    fun showPasswordValidationError(message: String) {
        view.showPasswordValidationError(message)
    }

    fun showConfirmPasswordValidationError(message: String) {
        view.showConfirmPasswordValidationError(message)
    }

    fun showLoading() {
        view.showLoading()
    }

    fun hideLoading() {
        view.hideLoading()
    }
}