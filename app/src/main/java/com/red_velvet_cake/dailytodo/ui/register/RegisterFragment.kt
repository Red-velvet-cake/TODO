package com.red_velvet_cake.dailytodo.ui.register

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.BuildConfig
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.databinding.FragmentRegisterBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import okio.IOException

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(), RegisterView {
    private val presenter = RegisterPresenter(this)

    companion object {
        const val TAG = "RegisterFragment"
    }

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun setUp() {
        presenter.registerAccount(
            "uniqueUsername3",
            "strongPassword",
            BuildConfig.TEAM_ID
        )

        presenter.showUsernameError("Username is too short")
        presenter.showPasswordError("Password is too short")
        presenter.showConfirmPasswordError("Passwords do not match")
    }

    override fun addCallBacks() {}

    override fun onRegisterAccountSuccess(registerAccountResponse: RegisterAccountResponse) {
        Log.d(TAG, "onRegisterAccountSuccess: $registerAccountResponse")
    }

    override fun onRegisterAccountFailure(exception: IOException) {
        Log.d(TAG, "onRegisterAccountFailure: ${exception.message}")
    }

    override fun onRegisterAccountClicked() {}

    override fun onLoginClicked() {}

    override fun isUsernameValid(username: String): Boolean {
        return username.length >= 4
    }

    override fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }

    override fun isConfirmPasswordValid(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    override fun showUsernameError(message: String) {
        binding.textInputLayoutUsername.error = message
    }

    override fun showPasswordError(message: String) {
        binding.textInputLayoutPassword.errorIconDrawable = null
        binding.textInputLayoutPassword.error = message
    }

    override fun showConfirmPasswordError(message: String) {
        binding.textInputLayoutConfirmPassword.error = message
    }

    override fun showLoading() {}

    override fun hideLoading() {}
}