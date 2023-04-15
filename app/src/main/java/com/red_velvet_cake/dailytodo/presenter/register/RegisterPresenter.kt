package com.red_velvet_cake.dailytodo.presenter.register

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
}