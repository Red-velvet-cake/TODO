package com.red_velvet_cake.dailytodo.ui.activity


import android.content.Intent
import android.view.LayoutInflater
import com.red_velvet_cake.dailytodo.data.local.SharedPrefs
import com.red_velvet_cake.dailytodo.databinding.ActivityHomeBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseActivity
import com.red_velvet_cake.dailytodo.ui.home.HomeFragment
import com.red_velvet_cake.dailytodo.utils.navigateTo

class MainActivity : BaseActivity<ActivityHomeBinding>(), MainView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate

    private val mainPresenter = MainPresenter(this)

    override fun setUp() {
        SharedPrefs.initPrefUtil(this)
        mainPresenter.checkLoggedInUser()
    }

    override fun addCallbacks() {}

    private fun lunchAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun navigateToLogin() {
        lunchAuthActivity()
    }

    override fun navigateToHome() {
        navigateTo(HomeFragment())
    }
}