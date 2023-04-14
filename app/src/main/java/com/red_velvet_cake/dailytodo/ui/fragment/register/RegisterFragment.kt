package com.red_velvet_cake.dailytodo.ui.fragment.register

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.RegisterAccountResponse
import com.red_velvet_cake.dailytodo.databinding.FragmentRegisterBinding
import com.red_velvet_cake.dailytodo.presenter.register.IRegisterView
import com.red_velvet_cake.dailytodo.presenter.register.RegisterPresenter
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(), IRegisterView {
    private val presenter = RegisterPresenter(this)

    companion object {
        const val TAG = "RegisterFragment"
    }

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun setUp() {


    }

    override fun addCallBacks() {}

    override fun onRegisterAccountSuccess(registerAccountResponse: RegisterAccountResponse) {
        Log.d(TAG, "onRegisterAccountSuccess: $registerAccountResponse")
    }

    override fun onRegisterAccountFailure(message: String) {
        Log.d(TAG, "onRegisterAccountFailure: $message")
    }

}