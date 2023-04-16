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
import okio.IOException


class LoginFragment : BaseFragment<FragmentLoginBinding>(), LoginView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
     private val LOG_TAG: String = LoginFragment::class.simpleName!!

    private val loginPresenter = LoginPresenter(this)
    override fun setUp() {
        setupSignUpTextView()
    }

    override fun addCallBacks() {
        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            if (validateInputFields(username, password)) {
                loginPresenter.loginUser(username, password)
            }
        }
    }

    override fun onSuccess(loginResponse: LoginResponse) {
        Log.d(LOG_TAG, "onLoginSuccess: ${loginResponse.loginResponseBody}")
    }

    override fun onFailure(exception: IOException) {
        Toast.makeText(requireContext(), exception.message, Toast.LENGTH_LONG).show()
    }

    private fun validateInputFields(username: String, password: String): Boolean {
        var isValid = true

        if (username.isBlank()) {
            binding.editTextUsername.error = "Username cannot be empty"
            isValid = false
        } else {
            binding.editTextUsername.error = null
        }

        if (password.isBlank()) {
            binding.editTextPassword.error = "Password cannot be empty"
            isValid = false
        } else {
            binding.editTextPassword.error = null
        }

        return isValid
    }

    private fun setupSignUpTextView() {
        val text = "Don’t have an account? sign up"
        val spannableString = SpannableString(text)
        val startIndex = text.indexOf("sign up")
        val endIndex = startIndex + "sign up".length
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {

            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(requireContext(), R.color.primary)
                ds.isUnderlineText = false
            }
        }
        spannableString.setSpan(
            clickableSpan,
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.textViewSignUp.text = spannableString
        binding.textViewSignUp.movementMethod = LinkMovementMethod.getInstance()
    }

}