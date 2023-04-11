package com.red_velvet_cake.dailytodo.ui.activity

import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.network.ApiClient
import com.red_velvet_cake.dailytodo.databinding.ActivityLoginBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseActivity
import com.red_velvet_cake.dailytodo.utils.ConnectionStatus

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override val LOG_TAG: String = LoginActivity::class.simpleName!!

    override val bindingInflater: (LayoutInflater) -> ActivityLoginBinding =
        ActivityLoginBinding::inflate
    private val apiClient = ApiClient()
    private val todoService = TodoServiceImpl(apiClient)
    override fun setUp() {
        setupSignUpTextView()

    }

    override fun addCallbacks() {
        binding?.loginButton?.setOnClickListener {
            // Assuming you have EditText views with the IDs 'usernameEditText' and 'passwordEditText'
            val username = binding.userNameTextField.text.toString().trim()
            val password = binding.passwordTextField.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                login(username, password)
            } else {
            }
        }
    }

    private fun login(username: String, password: String) {
        todoService.login(username, password) { success, result ->
            runOnUiThread {
                if (success) {
                } else {
                }
            }
        }
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
                // Handle sign up click here
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