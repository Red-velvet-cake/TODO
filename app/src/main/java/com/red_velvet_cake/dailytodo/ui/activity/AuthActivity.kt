package com.red_velvet_cake.dailytodo.ui.activity

import android.view.LayoutInflater
import com.red_velvet_cake.dailytodo.data.local.SharedPrefs
import com.red_velvet_cake.dailytodo.databinding.ActivityAuthBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseActivity

class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override val LOG_TAG: String
        get() = this::class.java.name

    override val bindingInflater: (LayoutInflater) -> ActivityAuthBinding
        get() = ActivityAuthBinding::inflate

    override fun setUp() {
        SharedPrefs.initPrefUtil(this)
    }
    override fun addCallbacks() {}
}