package com.red_velvet_cake.dailytodo.ui.login

import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import okio.IOException

interface LoginView {
    fun onSuccess(loginResponse: LoginResponse)
    fun onFailure(exception: Exception)
    fun showLoadingIndicator(show: Boolean)
    fun showUsernameError(show: Boolean)
    fun showPasswordError(show: Boolean)
    fun navigateToHome()
}