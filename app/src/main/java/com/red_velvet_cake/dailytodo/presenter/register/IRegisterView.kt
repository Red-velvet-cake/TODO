package com.red_velvet_cake.dailytodo.presenter.register

import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse

interface IRegisterView {
    fun onRegisterAccountSuccess(registerAccountResponse: RegisterAccountResponse)

    fun onRegisterAccountFailure(message: String)
}