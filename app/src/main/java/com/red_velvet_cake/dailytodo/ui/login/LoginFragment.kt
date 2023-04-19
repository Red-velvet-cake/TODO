package com.red_velvet_cake.dailytodo.ui.login

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.databinding.FragmentLoginBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.ui.home.HomeFragment
import com.red_velvet_cake.dailytodo.utils.navigateTo
import okio.IOException


class LoginFragment : BaseFragment<FragmentLoginBinding>(), LoginView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
    private val LOG_TAG: String = LoginFragment::class.simpleName!!

    private val loginPresenter = LoginPresenter(this)
    override fun setUp() {}

    override fun addCallBacks() {
        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            if (loginPresenter.validateInputFields(username, password)) {
                loginPresenter.loginUser(username, password)
            }
        }
    }

    override fun onSuccess(loginResponse: LoginResponse) {
        Log.d(LOG_TAG, "onLoginSuccess: ${loginResponse.loginResponseBody}")
    }

    override fun onFailure(exception: Exception) {
        Toast.makeText(requireContext(), exception.message, Toast.LENGTH_LONG).show()
    }


    override fun showLoadingIndicator(show: Boolean) {
        binding.loginProgressBar.visibility = if (show) View.VISIBLE else View.GONE
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
}