package com.red_velvet_cake.dailytodo.ui.register

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.red_velvet_cake.dailytodo.BuildConfig
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.databinding.FragmentRegisterBinding
import com.red_velvet_cake.dailytodo.ui.activity.MainActivity
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.utils.navigateBack

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(), RegisterView {
    private val presenter = RegisterPresenter(this)

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun setUp() {}

    override fun addCallBacks() {
        setupRegisterButtonClickListener()
        setupLoginButtonClickListener()
    }

    override fun disableRegisterButtonWithLoading() {
        runOnUiThread {
            binding.buttonRegister.apply {
                isEnabled = false
                text = getString(R.string.loading)
            }
        }
    }

    override fun enableRegisterButton() {
        runOnUiThread {
            binding.buttonRegister.apply {
                isEnabled = true
                text = getString(R.string.register)
            }
        }
    }

    override fun navigateToHome() {
        runOnUiThread {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun navigateToLogin() {
        requireActivity().navigateBack(R.id.fragment_container_view_auth)
    }

    override fun showUsernameValidationError() {
        binding.editTextUsername.error = getString(R.string.username_validation_error)
    }

    override fun showPasswordValidationError() {
        binding.editTextPassword.error = getString(R.string.password_validation_error)
    }

    override fun showConfirmPasswordValidationError() {
        binding.editTextConfirmPassword.error =
            getString(R.string.confirm_password_validation_error)
    }

    override fun showRegisterSuccessMessage() {
        showToast(getString(R.string.register_success))
    }

    override fun showRegisterFailedMessage(message: String) {
        showToast(message)
    }

    override fun showLoginFailedMessage(message: String) {
        showToast(message)
    }

    private fun setupRegisterButtonClickListener() {
        binding.buttonRegister.setOnClickListener {
            presenter.clickRegisterButton(
                binding.editTextUsername.text.toString(),
                binding.editTextPassword.text.toString(),
                binding.editTextConfirmPassword.text.toString(),
                BuildConfig.TEAM_ID,
            )
        }
    }

    private fun setupLoginButtonClickListener() {
        binding.textViewLogin.setOnClickListener {
            presenter.navigateToLogin()
        }
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun runOnUiThread(runnable: Runnable) {
        activity?.runOnUiThread(runnable)
    }

    companion object {
        fun newInstance(): RegisterFragment = RegisterFragment()
    }
}