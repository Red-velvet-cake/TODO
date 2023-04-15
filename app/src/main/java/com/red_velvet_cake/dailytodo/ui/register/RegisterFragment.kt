package com.red_velvet_cake.dailytodo.ui.register

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
    }

    override fun addCallBacks() {}
    override fun handleRegisterAccountSuccess(registerAccountResponse: RegisterAccountResponse) {
        TODO("Not yet implemented")
    }

    override fun handleRegisterAccountFailure(exception: IOException) {
        TODO("Not yet implemented")
    }

    override fun handleRegisterButtonClick() {
        TODO("Not yet implemented")
    }

    override fun handleLoginButtonClick() {
        TODO("Not yet implemented")
    }

    override fun validateUsername(username: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun validatePassword(password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun showUsernameValidationError(message: String) {
        TODO("Not yet implemented")
    }

    override fun showPasswordValidationError(message: String) {
        TODO("Not yet implemented")
    }

    override fun showConfirmPasswordValidationError(message: String) {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }
}