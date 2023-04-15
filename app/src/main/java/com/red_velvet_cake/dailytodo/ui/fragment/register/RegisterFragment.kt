package com.red_velvet_cake.dailytodo.ui.fragment.register

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.BuildConfig
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.databinding.FragmentRegisterBinding
import com.red_velvet_cake.dailytodo.presenter.register.RegisterPresenter
import com.red_velvet_cake.dailytodo.presenter.register.RegisterView
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import okio.IOException

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(), RegisterView {
    private val presenter = RegisterPresenter(this)

    companion object {
        const val TAG = "RegisterFragment"
    }

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun setUp() {
        presenter.registerAccount(
            "uniqueUsername",
            "strongPassword",
            BuildConfig.TEAM_ID
        )
    }

    override fun addCallBacks() {}

    override fun onRegisterAccountSuccess(registerAccountResponse: RegisterAccountResponse) {
        Log.d(TAG, "onRegisterAccountSuccess: $registerAccountResponse")
    }

    override fun onRegisterAccountFailure(exception: IOException) {
        Log.d(TAG, "onRegisterAccountFailure: ${exception.message}")
    }

}