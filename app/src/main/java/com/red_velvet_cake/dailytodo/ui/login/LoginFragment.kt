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
import androidx.core.content.ContextCompat
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.LoginResponse
import com.red_velvet_cake.dailytodo.databinding.FragmentLoginBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.ui.register.RegisterFragment
import com.red_velvet_cake.dailytodo.utils.navigateExclusive
import okio.IOException


class LoginFragment : BaseFragment<FragmentLoginBinding>(), LoginView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
    val LOG_TAG: String = LoginFragment::class.simpleName!!

    private val loginPresenter = LoginPresenter(this)
    override fun setUp() {
        setupSignUpTextView()
    }

    override fun addCallBacks() {
        binding.loginButton.setOnClickListener {
            val username = binding.userNameTextField.text.toString()
            val password = binding.passwordTextField.text.toString()
            loginPresenter.loginUser(username, password)
        }
        binding.signUpTextView.setOnClickListener {
            requireActivity().navigateExclusive(RegisterFragment.newInstance())
        }
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        Log.d(LOG_TAG, "onLoginSuccess: ${loginResponse.loginResponseBody}")
    }

    override fun onLoginFailure(exception: IOException) {
        Log.d(LOG_TAG, "onLoginSuccess: ${exception.message}")
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
        binding.signUpTextView.text = spannableString
        binding.signUpTextView.movementMethod = LinkMovementMethod.getInstance()
    }

}