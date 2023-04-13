package com.red_velvet_cake.dailytodo.presenter.register

import com.red_velvet_cake.dailytodo.data.TodoService
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import java.io.IOException

class RegisterPresenter(
    private val view: IRegisterView,
    private val service: TodoService
) {
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

    private fun onRegisterAccountFailure(error: IOException) {
        view.onRegisterAccountFailure(error.message ?: "Unknown error")
    }
}