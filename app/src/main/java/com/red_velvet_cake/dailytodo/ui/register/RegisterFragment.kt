package com.red_velvet_cake.dailytodo.ui.register

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.red_velvet_cake.dailytodo.BuildConfig
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.databinding.FragmentRegisterBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import okio.IOException

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(), RegisterView {
    private val presenter = RegisterPresenter(this)

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun setUp() {}

    override fun addCallBacks() {
        binding.buttonRegister.setOnClickListener {
            handleRegisterButtonClick()
        }

        binding.textViewLogin.setOnClickListener {
            handleLoginButtonClick()
        }
    }

    override fun handleRegisterAccountSuccess(registerAccountResponse: RegisterAccountResponse) {
        hideLoading()
        if (registerAccountResponse.isSuccess) {
            showToast(getString(R.string.register_success))
        } else {
            showToast(registerAccountResponse.message)
        }
    }

    override fun handleRegisterAccountFailure(exception: IOException) {
        showToast(exception.message.toString())
    }

    override fun handleRegisterButtonClick() {
        if (validateForm()) {
            showLoading()
            val username = binding.editTextUsername.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            presenter.registerAccount(username, password, BuildConfig.TEAM_ID)
        }
    }

    private fun validateForm(): Boolean {
        val username = binding.editTextUsername.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val confirmPassword = binding.editTextConfirmPassword.text.toString().trim()

        when {
            !validateUsername(username) -> {
                showUsernameValidationError(getString(R.string.username_validation_error))
                return false
            }

            !validatePassword(password) -> {
                showPasswordValidationError(getString(R.string.password_validation_error))
                return false
            }

            !validateConfirmPassword(password, confirmPassword) -> {
                showConfirmPasswordValidationError(getString(R.string.confirm_password_validation_error))
                return false
            }

            else -> return true
        }
    }

    override fun handleLoginButtonClick() {}

    override fun validateUsername(username: String): Boolean {
        return username.length >= 4
    }

    override fun validatePassword(password: String): Boolean {
        return password.length >= 6
    }

    override fun validateConfirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    override fun showUsernameValidationError(message: String) {
        binding.editTextUsername.error = message
    }

    override fun showPasswordValidationError(message: String) {
        binding.editTextPassword.error = message
    }

    override fun showConfirmPasswordValidationError(message: String) {
        binding.editTextConfirmPassword.error = message
    }

    override fun showLoading() {
        activity?.runOnUiThread {
            binding.buttonRegister.apply {
                isEnabled = false
                text = getString(R.string.loading)
            }
        }
    }

    override fun hideLoading() {
        activity?.runOnUiThread {
            binding.buttonRegister.apply {
                isEnabled = true
                text = getString(R.string.register)
            }
        }
    }

    private fun showToast(message: String) {
        activity?.runOnUiThread {
            Toast.makeText(
                activity,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}