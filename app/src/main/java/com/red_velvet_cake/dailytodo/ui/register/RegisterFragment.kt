package com.red_velvet_cake.dailytodo.ui.register

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.databinding.FragmentRegisterBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.utils.RegisterFormError

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(), RegisterView {
    private val presenter = RegisterPresenter(this)

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun setUp() {}

    override fun addCallBacks() {
        onClickRegisterButton()
        onLoginButtonClicked()
    }

    override fun showLoading() {
        runOnUiThread {
            binding.buttonRegister.apply {
                isEnabled = false
                text = getString(R.string.loading)
            }
        }
    }

    override fun hideLoading() {
        runOnUiThread {
            binding.buttonRegister.apply {
                isEnabled = true
                text = getString(R.string.register)
            }
        }
    }

    override fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(
                activity,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun navigateToHome() {}

    override fun navigateToLogin() {}

    override fun showValidationError(error: RegisterFormError) {
        when (error) {
            RegisterFormError.USERNAME_INVALID -> {
                binding.editTextUsername.error = getString(R.string.username_validation_error)
            }

            RegisterFormError.PASSWORD_INVALID -> {
                binding.editTextPassword.error = getString(R.string.password_validation_error)
            }

            RegisterFormError.CONFIRM_PASSWORD_NOT_MATCHING -> {
                binding.editTextConfirmPassword.error =
                    getString(R.string.confirm_password_validation_error)
            }
        }
    }

    private fun onClickRegisterButton() {
        binding.buttonRegister.setOnClickListener {
            presenter.clickRegisterButton(
                binding.editTextUsername.text.toString().trim(),
                binding.editTextPassword.text.toString().trim(),
                binding.editTextConfirmPassword.text.toString().trim()
            )
        }
    }

    private fun onLoginButtonClicked() {
        binding.textViewLogin.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun runOnUiThread(runnable: Runnable) {
        activity?.runOnUiThread(runnable)
    }

    companion object {
        fun newInstance(): RegisterFragment = RegisterFragment()
    }
}