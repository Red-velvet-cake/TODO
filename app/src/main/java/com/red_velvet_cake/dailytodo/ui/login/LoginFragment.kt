package com.red_velvet_cake.dailytodo.ui.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.databinding.FragmentLoginBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.ui.home.HomeFragment
import com.red_velvet_cake.dailytodo.ui.register.RegisterFragment
import com.red_velvet_cake.dailytodo.utils.navigateTo


class LoginFragment : BaseFragment<FragmentLoginBinding>(), LoginView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
    private val LOG_TAG: String = LoginFragment::class.simpleName!!

    private val loginPresenter = LoginPresenter(this)
    override fun setUp() {}

    override fun addCallBacks() {
        setupLoginButtonClickListener()
        setupRegisterTextClickListener()
    }

    override fun hideLoadingState() {
        binding.loginProgressBar.visibility = View.GONE
    }

    override fun showLoginFailedMessage(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun showLoadingState() {
        binding.loginProgressBar.visibility = View.VISIBLE
    }

    override fun showUsernameError(show: Boolean) {
        binding.editTextUsername.error = if (show) getString(R.string.username_empty) else null
    }

    override fun showPasswordError(show: Boolean) {
        binding.editTextPassword.error = if (show) getString(R.string.password_empty) else null
    }

    override fun navigateToHome() {
        requireActivity().navigateTo(HomeFragment())
    }

    override fun navigateToRegister() {
        requireActivity().navigateTo(
            RegisterFragment.newInstance(),
            R.id.fragment_container_view_auth
        )
    }

    private fun setupLoginButtonClickListener() {
        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            if (loginPresenter.validateInputFields(username, password)) {
                loginPresenter.loginUser(username, password)
            }
        }
    }

    private fun setupRegisterTextClickListener() {
        binding.textViewSignup.setOnClickListener {
            loginPresenter.navigateToRegister()
        }
    }
}