package com.red_velvet_cake.dailytodo.presenter.register

import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import okio.IOException

interface RegisterView {
    fun onRegisterAccountSuccess(registerAccountResponse: RegisterAccountResponse)

    fun onRegisterAccountFailure(exception: IOException)
}