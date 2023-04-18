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
        view.showLoadingIndicator(false)
        view.onSuccess(loginResponse)
    }

    private fun onFailure(exception: IOException) {
        view.showLoadingIndicator(false)
        view.onFailure(exception)
    }
}