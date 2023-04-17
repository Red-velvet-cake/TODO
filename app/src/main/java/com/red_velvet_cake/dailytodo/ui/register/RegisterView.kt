package com.red_velvet_cake.dailytodo.ui.register

import com.red_velvet_cake.dailytodo.utils.RegisterFormError

interface RegisterView {

    fun showToast(message: String)

    fun navigateToHome()

    fun navigateToLogin()

    fun showValidationError(error: RegisterFormError)

    fun showRegisterButtonLoadingState()

    fun showRegisterButtonEnabledState()

}