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
            ::onRegisterAccountSuccess,
            ::onRegisterAccountFailure
        )
    }

    private fun onRegisterAccountSuccess(registerAccountResponse: RegisterAccountResponse) {
        view.onRegisterAccountSuccess(registerAccountResponse)
    }

    private fun onRegisterAccountFailure(exception: IOException) {
        view.onRegisterAccountFailure(exception)
    }

    fun onRegisterAccountClicked() {
        view.onRegisterAccountClicked()
    }

    fun onLoginClicked() {
        view.onLoginClicked()
    }

    fun isUsernameValid(username: String): Boolean {
        return view.isUsernameValid(username)
    }

    fun isPasswordValid(password: String): Boolean {
        return view.isPasswordValid(password)
    }

    fun isConfirmPasswordValid(password: String, confirmPassword: String): Boolean {
        return view.isConfirmPasswordValid(password, confirmPassword)
    }

    fun showUsernameError(message: String) {
        view.showUsernameError(message)
    }

    fun showPasswordError(message: String) {
        view.showPasswordError(message)
    }

    fun showConfirmPasswordError(message: String) {
        view.showConfirmPasswordError(message)
    }

    fun showLoading() {
        view.showLoading()
    }

    fun hideLoading() {
        view.hideLoading()
    }
}