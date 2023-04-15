package com.red_velvet_cake.dailytodo.ui.register

import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import okio.IOException

interface RegisterView {
    fun onRegisterAccountSuccess(registerAccountResponse: RegisterAccountResponse)

    fun onRegisterAccountFailure(exception: IOException)

    fun onRegisterAccountClicked()

    fun onLoginClicked()

    fun isUsernameValid(username: String): Boolean

    fun isPasswordValid(password: String): Boolean

    fun isConfirmPasswordValid(password: String, confirmPassword: String): Boolean

    fun showUsernameError(message: String)

    fun showPasswordError(message: String)

    fun showConfirmPasswordError(message: String)

    fun showLoading()

    fun hideLoading()
}