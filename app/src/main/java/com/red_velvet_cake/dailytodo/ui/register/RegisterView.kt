package com.red_velvet_cake.dailytodo.ui.register

import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import okio.IOException

interface RegisterView {
    fun handleRegisterAccountSuccess(registerAccountResponse: RegisterAccountResponse)

    fun handleRegisterAccountFailure(exception: IOException)

    fun handleRegisterButtonClick()

    fun handleLoginButtonClick()

    fun validateUsername(username: String): Boolean

    fun validatePassword(password: String): Boolean

    fun validateConfirmPassword(password: String, confirmPassword: String): Boolean

    fun showUsernameValidationError(message: String)

    fun showPasswordValidationError(message: String)

    fun showConfirmPasswordValidationError(message: String)

    fun showLoading()

    fun hideLoading()
}