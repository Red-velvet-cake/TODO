package com.red_velvet_cake.dailytodo.presenter.login

import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.model.login.LoginRequest
import com.red_velvet_cake.dailytodo.model.login.LoginResponse
import okio.IOException

class LoginPresenter(private val view: IloginView) {
    private val todoServiceImpl = TodoServiceImpl()

    fun loginUser(username: String, password: String) {
        val loginRequest = LoginRequest(username, password)
        todoServiceImpl.loginUser(
            loginRequest,
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