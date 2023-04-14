package com.red_velvet_cake.dailytodo.ui.activity

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.orhanobut.hawk.Hawk
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.databinding.ActivityLoginBinding
import com.red_velvet_cake.dailytodo.model.login.LoginResponse
import com.red_velvet_cake.dailytodo.presenter.login.IloginView
import com.red_velvet_cake.dailytodo.presenter.login.LoginPresenter
import com.red_velvet_cake.dailytodo.ui.base.BaseActivity
import com.red_velvet_cake.dailytodo.utils.ConnectionStatus
import okio.IOException

class LoginActivity : BaseActivity<ActivityLoginBinding>(), IloginView {
    override val LOG_TAG: String = LoginActivity::class.simpleName!!

    override val bindingInflater: (LayoutInflater) -> ActivityLoginBinding =
        ActivityLoginBinding::inflate

    private val loginPresenter = LoginPresenter(this)

    override fun setUp() {
        setupSignUpTextView()
    }

    override fun addCallbacks() {
        binding.loginButton.setOnClickListener {
            val username = binding.userNameTextField.text.toString()
            val password = binding.passwordTextField.text.toString()
            loginPresenter.loginUser(username, password)
        }
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        Log.d(LOG_TAG, "onLoginSuccess: ${loginResponse.username}")
        Hawk.put("auth_token", loginResponse.token)
    }

    override fun onLoginFailure(exception: IOException) {
        Log.d(LOG_TAG, "onLoginSuccess: ${exception.message}")
    }

    override fun isInternetAvailable(connectionStatus: ConnectionStatus) {
        when (connectionStatus) {
            ConnectionStatus.Available -> Toast.makeText(
                this,
                ConnectionStatus.Available.name,
                Toast.LENGTH_SHORT
            )
                .show()
            ConnectionStatus.Unavailable -> Toast.makeText(
                this,
                ConnectionStatus.Unavailable.name,
                Toast.LENGTH_SHORT
            )
                .show()
        }
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
                ds.color = ContextCompat.getColor(this@LoginActivity, R.color.primary)
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