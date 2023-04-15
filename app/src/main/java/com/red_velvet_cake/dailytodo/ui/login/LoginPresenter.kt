package com.red_velvet_cake.dailytodo.ui.login

import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import okio.IOException

class LoginPresenter(private val view: LoginView) {
    private val todoServiceImpl = TodoServiceImpl()

    fun loginUser(username: String, password: String) {
        todoServiceImpl.loginUser(
            username,
            password,
            ::onLoginSuccess,
            ::onLoginFailure
        )
    }

    private fun onLoginSuccess(loginResponse: LoginResponse) {
        view.onLoginSuccess(loginResponse)
    }

    private fun onLoginFailure(exception: IOException) {
        view.onLoginFailure(exception)
    }
}